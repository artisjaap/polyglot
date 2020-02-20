package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.core.action.to.AnswerTO;
import be.artisjaap.polyglot.core.action.to.JournalFilterTO;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface LanguagePracticeJournalRepositoryCustom {


    void addAnswerToList(AnswerTO answer, ObjectId lessonId);

    Optional<LanguagePracticeJournal> findByUserIdAndLessonId(ObjectId userId, ObjectId lessonId);

    Optional<LanguagePracticeJournal> findByFilters(JournalFilterTO journalFilterTO);

    Optional<LanguagePairMistakes> findMistakesByFilters(JournalFilterTO journalFilterTO);
}
