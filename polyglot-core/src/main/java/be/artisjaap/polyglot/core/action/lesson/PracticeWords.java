package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.polyglot.core.action.assembler.TranslationPracticeAssembler;
import be.artisjaap.polyglot.core.action.journal.JournalPracticeResults;
import be.artisjaap.polyglot.core.action.lesson.AnswerChecker;
import be.artisjaap.polyglot.core.action.lesson.FindPracticeWord;
import be.artisjaap.polyglot.core.action.lesson.NextRandomWord;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.pairs.LanguagePairTurn;
import be.artisjaap.polyglot.core.action.pairs.UpdateLanguagePairHealth;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.core.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static be.artisjaap.polyglot.core.action.to.test.OrderType.NORMAL;
import static be.artisjaap.polyglot.core.action.to.test.OrderType.REVERSE;

@Component
public class PracticeWords {

    @Autowired
    private TranslationPracticeRepository translationPracticeRepository;

    @Autowired
    private FindLanguagePair findLanguagePair;

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private LanguagePairTurn languagePairTurn;

    @Autowired
    private TranslationPracticeAssembler translationPracticeAssembler;

    @Autowired
    private UpdateLanguagePairHealth updateLanguagePairHealth;

    @Autowired
    private JournalPracticeResults journalPracticeResults;

    @Autowired
    private NextRandomWord nextRandomWord;

    @Autowired
    private FindPracticeWord findPracticeWord;

    @Autowired
    private AnswerChecker answerChecker;


    public PracticeWordTO nextWord(String userId, String languagePairId, OrderType orderType) {
        return nextRandomWord.nextWord(userId, languagePairId, orderType);
    }


    public AnswerAndNextWordTO checkAnswerAndGiveNext(PracticeWordCheckTO practiceWordCheckTO) {
        TranslationPractice translationPractice = translationPracticeRepository.findByUserIdAndTranslationId(new ObjectId(practiceWordCheckTO.userId()), new ObjectId(practiceWordCheckTO.translationId()));
        Translation translation = translationRepository.findById(new ObjectId(practiceWordCheckTO.translationId())).orElseThrow(IllegalStateException::new);
        LanguagePairTO languagePairTO = findLanguagePair.byId(translation.getLanguagePairId().toString());


        AnswerTO.Builder answerTOBuilder = AnswerTO.newBuilder()
                .withLanguagePairId(languagePairTO.id())
                .withUserId(practiceWordCheckTO.userId())
                .withTranslationId(practiceWordCheckTO.translationId())
                .withGivenAnswer(practiceWordCheckTO.answerGiven());

        if (practiceWordCheckTO.answerOrderType() == NORMAL) {
            if (answerChecker.checkFor(translation.getLanguageB(), practiceWordCheckTO.answerGiven())) {
                translationPractice.answerCorrect();
                updateLanguagePairHealth.updateCorrect(languagePairTO.id());
                answerTOBuilder.withCorrectAnswer(true)
                        .withExpectedAnswer(translation.getLanguageB())
                        .withQuestion(translation.getLanguageA());

            } else {
                translationPractice.answerIncorrect();
                updateLanguagePairHealth.updateIncorrect(languagePairTO.id());
                answerTOBuilder.withCorrectAnswer(false)
                        .withExpectedAnswer(translation.getLanguageB())
                        .withQuestion(translation.getLanguageA());

            }

        } else if (practiceWordCheckTO.answerOrderType() == REVERSE) {
            if (answerChecker.checkFor(translation.getLanguageA(), practiceWordCheckTO.answerGiven())) {
                translationPractice.answerCorrectReverse();
                updateLanguagePairHealth.updateCorrect(languagePairTO.id());

                answerTOBuilder.withCorrectAnswer(true)
                        .withExpectedAnswer(translation.getLanguageA())
                        .withQuestion(translation.getLanguageB());

            } else {
                translationPractice.answerIncorrectReverse();
                updateLanguagePairHealth.updateIncorrect(languagePairTO.id());

                answerTOBuilder.withCorrectAnswer(false)
                        .withExpectedAnswer(translation.getLanguageA())
                        .withQuestion(translation.getLanguageB());

            }

        }

        translationPracticeRepository.save(translationPractice);

        PracticeWordTO practiceWordTO = nextWord(practiceWordCheckTO.userId(), languagePairTO.id(), practiceWordCheckTO.nextOrderType());


        AnswerTO answerTO = answerTOBuilder.build();
        journalPracticeResults.forResult(answerTO, Optional.ofNullable(practiceWordCheckTO.lessonId()));

        return AnswerAndNextWordTO.newBuilder()
                .withAnswer(answerTO)
                .withPracticeWord(practiceWordTO)
                .build();

    }


    public void practiced(String userId, String translationId, Boolean reversed) {
        TranslationPractice translationPractice = translationPracticeRepository.findByUserIdAndTranslationId(new ObjectId(userId), new ObjectId(translationId));
        saveTranslationTurn(translationPractice, reversed);
        languagePairTurn.saveTurnOn(translationPractice.getLanguagePairId().toString(), reversed);

    }



    public List<TranslationPracticeTO> allPracticedWords(String userId, String languagePairId, List<ProgressStatus> progressStatuses) {
        return translationPracticeAssembler.assembleTOs(translationPracticeRepository.findByUserIdAndLanguagePairIdAndProgressStatusIn(new ObjectId(userId), new ObjectId(languagePairId), progressStatuses));
    }

    public List<PracticeWordTO> givePracticeWordsForTranslations(OrderType orderType, List<String> translationIds) {
        return findPracticeWord.forTranslations(translationIds, orderType);
    }























    private void saveTranslationTurn(TranslationPractice translationPractice, Boolean reversed) {
        if (reversed) {
            translationPractice.increaseAnswerChecked();
        } else {
            translationPractice.increaseAnswerCheckedReverse();
        }
        translationPracticeRepository.save(translationPractice);
    }










}
