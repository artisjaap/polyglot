package be.artisjaap.polyglot.core.model;

import be.artisjaap.core.utils.LocalDateUtils;
import be.artisjaap.polyglot.core.action.to.AnswerTO;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


public class LanguagePracticeJournalRepositoryImpl implements LanguagePracticeJournalRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LanguagePracticeJournalRepository languagePracticeJournalRepository;

    @Override
    public void addAnswerToList(AnswerTO answer) {
        TranslationJournal journal = TranslationJournal.newBuilder()
                .withCorrect(answer.correctAnswer())
                .withExpectedAnswer(answer.expectedAnswer())
                .withGivenAnswer(answer.givenAnswer())
                .withQuestion(answer.question())
                .withTranslationId(new ObjectId(answer.translationId()))
                .build();
        ObjectId languagePairId = new ObjectId(answer.languagePairId());
        ObjectId userId = new ObjectId(answer.userId());
        String nowYearMonth = LocalDateUtils.nowYearMonthAsString();

        Query q = new Query();
        q.addCriteria(Criteria.where("userId").is(userId)
                .and("languagePairId").is(languagePairId)
                .and("yearMonth").is(nowYearMonth));
        Update update = new Update().push("translationJournalList", journal);

        UpdateResult updateResult = mongoTemplate.updateFirst(q, update, LanguagePracticeJournal.class);
        if(updateResult.getModifiedCount() == 0){
            LanguagePracticeJournal languagePracticeJournal = LanguagePracticeJournal.newBuilder()
                    .withLanguagePairId(languagePairId)
                    .withUserId(userId)
                    .withYearMonth(nowYearMonth)
                    .addTranslationJournalList(journal)
                    .build();
            languagePracticeJournalRepository.save(languagePracticeJournal);
        }

    }
}
