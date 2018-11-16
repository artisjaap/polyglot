package be.artisjaap.polyglot.core.model;

import be.artisjaap.polyglot.core.action.to.AnswerTO;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface LanguagePracticeJournalRepositoryCustom {


    void addAnswerToList(AnswerTO answer, ObjectId lessonId);

    Optional<LanguagePracticeJournal> findByUserIdAndLessonId(ObjectId userId, ObjectId lessonId);

}
