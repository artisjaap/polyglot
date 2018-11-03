package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LanguagePracticeJournalRepository extends MongoRepository<LanguagePracticeJournal, ObjectId>, LanguagePracticeJournalRepositoryCustom {

    Optional<LanguagePracticeJournal> findByUserIdAndLanguagePairIdAndYearMonth(ObjectId userId, ObjectId languagePairId, String yearMonth);

}
