package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.polyglot.core.model.ProgressStatus;
import be.artisjaap.polyglot.core.model.TranslationPractice;
import be.artisjaap.polyglot.core.model.TranslationPracticeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IntroduceNewWordForPractice {

    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;

    public Optional<TranslationPractice> forUserInLanguage(String userId, String languagePairId) {
        Optional<TranslationPractice> top1ByUserIdAndLanguagePairIdAndStatus = translationPracticeRepository.findTop1ByUserIdAndLanguagePairIdAndProgressStatus(new ObjectId(userId), new ObjectId(languagePairId), ProgressStatus.NEW.NEW);
        top1ByUserIdAndLanguagePairIdAndStatus.ifPresent(t -> {
            t.setProgressStatus(ProgressStatus.IN_PROGRESS);
            translationPracticeRepository.save(t);
        });
        return top1ByUserIdAndLanguagePairIdAndStatus;
    }
}
