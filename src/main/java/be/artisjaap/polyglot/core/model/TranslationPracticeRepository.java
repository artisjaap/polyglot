package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TranslationPracticeRepository extends MongoRepository<TranslationPractice, ObjectId> {

    List<TranslationPractice> findByUserIdAndLanguagePairIdAndProgressStatus(ObjectId userId, ObjectId languagePairId, ProgressStatus status);

    Optional<TranslationPractice> findTop1ByUserIdAndLanguagePairIdAndProgressStatus(ObjectId userId, ObjectId languagePairId, ProgressStatus status);

    TranslationPractice findByUserIdAndTranslationId(ObjectId userId, ObjectId translationId);

    List<TranslationPractice> findByUserIdAndLanguagePairIdAndProgressStatusIn(ObjectId userId, ObjectId languagePairId, List<ProgressStatus> progressStatuses);
}
