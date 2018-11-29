package be.artisjaap.polyglot.core.model;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.polyglot.core.action.to.AnswerTO;
import be.artisjaap.polyglot.core.action.to.JournalFilterTO;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


public class LanguagePracticeJournalRepositoryImpl implements LanguagePracticeJournalRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LanguagePracticeJournalRepository languagePracticeJournalRepository;

    @Override
    public void addAnswerToList(AnswerTO answer, ObjectId lessonId) {
        TranslationJournal journal = TranslationJournal.newBuilder()
                .withLessonId(lessonId)
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

    @Override
    public Optional<LanguagePracticeJournal> findByUserIdAndLessonId(ObjectId userId, ObjectId lessonId) {
        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(match(Criteria.where("userId").is(userId).and("translationJournalList.lessonId").is(lessonId)));
        operations.add(unwind("translationJournalList"));
        operations.add(match(Criteria.where("userId").is(userId).and("translationJournalList.lessonId").is(lessonId)));
        operations.add(group().first("userId").as("").addToSet("translationJournalList").as("translationJournalList"));
        Aggregation agg = newAggregation(operations);

        AggregationResults<LanguagePracticeJournal> groupResults = mongoTemplate.aggregate(agg, LanguagePracticeJournal.class, LanguagePracticeJournal.class);
        List<LanguagePracticeJournal> journals = StreamSupport.stream(groupResults.spliterator(),false).collect(Collectors.toList());
        if(journals.size() == 1){
            return Optional.of(journals.get(0));
        } else if (journals.size() == 0) {
            return Optional.empty();
        }

        throw new UnsupportedOperationException("Resultset is > 0");

    }

    @Override
    public Optional<LanguagePracticeJournal> findByFilters(JournalFilterTO journalFilterTO) {
        Criteria criteria = Criteria.where("userId").is(new ObjectId(journalFilterTO.userId()));
        if(journalFilterTO.lessonId() != null) {
            criteria.and("translationJournalList.lessonId").is(new ObjectId(journalFilterTO.lessonId()));
        }
        if(journalFilterTO.from() != null){
            if(journalFilterTO.until() != null){
                criteria.and("translationJournalList.timestamp").gte(journalFilterTO.from()).lte(journalFilterTO.until());
            }else {
                criteria.and("translationJournalList.timestamp").gte(journalFilterTO.from());
            }
        }else if(journalFilterTO.until() != null){
            criteria.and("translationJournalList.timestamp").lte(journalFilterTO.until());
        }

        List<AggregationOperation> operations = new ArrayList<>();
        operations.add(match(criteria));
        operations.add(unwind("translationJournalList"));
        operations.add(match(criteria));
        operations.add(group().first("userId").as("userId")
                .first("languagePairId").as("languagePairId")
                .addToSet("translationJournalList").as("translationJournalList"));
        Aggregation agg = newAggregation(operations);

        AggregationResults<LanguagePracticeJournal> groupResults = mongoTemplate.aggregate(agg, LanguagePracticeJournal.class, LanguagePracticeJournal.class);
        List<LanguagePracticeJournal> journals = StreamSupport.stream(groupResults.spliterator(),false).collect(Collectors.toList());
        if(journals.size() == 1){
            return Optional.of(journals.get(0));
        } else if (journals.size() == 0) {
            return Optional.empty();
        }

        throw new UnsupportedOperationException("Resultset is > 0");
    }
}
