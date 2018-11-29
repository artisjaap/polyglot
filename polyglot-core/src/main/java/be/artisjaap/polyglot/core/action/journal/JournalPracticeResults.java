package be.artisjaap.polyglot.core.action.journal;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.core.utils.MongoUtils;
import be.artisjaap.polyglot.core.action.assembler.LanguagePracticeJournalAssembler;
import be.artisjaap.polyglot.core.action.to.AnswerTO;
import be.artisjaap.polyglot.core.action.to.JournalFilterTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;
import be.artisjaap.polyglot.core.model.LanguagePracticeJournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Component
public class JournalPracticeResults {

    @Autowired
    private LanguagePracticeJournalRepository languagePracticeJournalRepository;

    @Autowired
    private LanguagePracticeJournalAssembler languagePracticeJournalAssembler;

    public void forResult(AnswerTO answerTO, Optional<String> lessonId){
        languagePracticeJournalRepository.addAnswerToList(answerTO, lessonId.map(ObjectId::new).orElse(null));
    }

    public LanguagePracticeJournalTO findJournalFor(JournalFilterTO journalFilterTO){
        return languagePracticeJournalRepository.findByFilters(journalFilterTO)
                .map(languagePracticeJournalAssembler::assembleTO)
                .orElseGet(() -> LanguagePracticeJournalTO.newBuilder()
                        .withUserId(journalFilterTO.userId())
                        .withLanguagePairId(journalFilterTO.languagePairId())
                        .withFrom(journalFilterTO.from())
                        .withUntil(journalFilterTO.until())
                        .build());
    }

    public LanguagePracticeJournalTO findJournalFor(String userId, String languagePairId, YearMonth yearMonth){
        return languagePracticeJournalRepository.findByUserIdAndLanguagePairIdAndYearMonth(MongoUtils.toObjectId(userId)
                , MongoUtils.toObjectId(languagePairId)
                , yearMonth.toString()).map(languagePracticeJournalAssembler::assembleTO)
                .orElseGet(() -> LanguagePracticeJournalTO.newBuilder().build());
    }

    public LanguagePracticeJournalTO findJournalFor(String userId, String languagePairId, LocalDate date){
        LanguagePracticeJournalTO dataForMonth = findJournalFor(userId, languagePairId, YearMonth.from(date));
        List<TranslationJournalTO> filteredForDay = dataForMonth.translationJournalList().stream()
                .filter(translationJournalTO -> LocalDateUtils.timestampInDay(translationJournalTO.timestamp(), date)).collect(Collectors.toList());
        return LanguagePracticeJournalTO.newBuilder(dataForMonth).withTranslationJournalList(filteredForDay).build();

    }


    public LanguagePracticeJournalTO findJournalForUserAndLesson(String userId, String lessonId) {
        return languagePracticeJournalRepository.findByUserIdAndLessonId(new ObjectId(userId), new ObjectId(lessonId))
            .map(languagePracticeJournalAssembler::assembleTO)
            .orElseGet(() -> LanguagePracticeJournalTO.newBuilder().build());
    }

    public LanguagePracticeJournalTO findJournalForUserAndLesson(String userId, String lessonId, LocalDate date) {
        LanguagePracticeJournalTO dataForMonth = findJournalForUserAndLesson(userId, lessonId);
        List<TranslationJournalTO> filteredForDay = dataForMonth.translationJournalList().stream()
                .filter(translationJournalTO -> LocalDateUtils.timestampInDay(translationJournalTO.timestamp(), date)).collect(Collectors.toList());
        return LanguagePracticeJournalTO.newBuilder(dataForMonth).withTranslationJournalList(filteredForDay).build();

    }

    public LanguagePracticeJournalTO findJournalForUserAndLesson(String userId, String lessonId, YearMonth yearMonth) {
        LanguagePracticeJournalTO dataForMonth = findJournalForUserAndLesson(userId, lessonId);
        List<TranslationJournalTO> filteredForDay = dataForMonth.translationJournalList().stream()
                .filter(translationJournalTO -> LocalDateUtils.timestampInMonth(translationJournalTO.timestamp(), yearMonth)).collect(Collectors.toList());
        return LanguagePracticeJournalTO.newBuilder(dataForMonth).withTranslationJournalList(filteredForDay).build();

    }
}
