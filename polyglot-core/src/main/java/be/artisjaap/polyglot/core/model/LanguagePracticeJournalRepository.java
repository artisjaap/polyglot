package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LanguagePracticeJournalRepository extends MongoRepository<LanguagePracticeJournal, ObjectId>, LanguagePracticeJournalRepositoryCustom {

    @Cacheable("languagePracticeJournal")
    Optional<LanguagePracticeJournal> findByUserIdAndLanguagePairIdAndYearMonth(ObjectId userId, ObjectId languagePairId, String yearMonth);


}
