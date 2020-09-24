package be.artisjaap.polyglot.core.action.lesson;

import be.artisjaap.polyglot.core.action.assembler.TranslationPracticeAssembler;
import be.artisjaap.polyglot.core.action.journal.JournalPracticeResults;
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
public class FindPracticeWords {

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

        PracticeWordTO practiceWordTO = nextWord(practiceWordCheckTO.userId(), languagePairTO.id(), practiceWordCheckTO.nextOrderType());


        AnswerTO answerTO = checkPracticeAnswer(practiceWordCheckTO, translationPractice, translation, languagePairTO);


        return AnswerAndNextWordTO.newBuilder()
                .withAnswer(answerTO)
                .withPracticeWord(practiceWordTO)
                .build();

    }

    public AnswerTO checkAnswer(PracticeWordCheckTO practiceWordCheckTO) {

        TranslationPractice translationPractice = translationPracticeRepository.findByUserIdAndTranslationId(new ObjectId(practiceWordCheckTO.userId()), new ObjectId(practiceWordCheckTO.translationId()));
        Translation translation = translationRepository.findById(new ObjectId(practiceWordCheckTO.translationId())).orElseThrow(IllegalStateException::new);
        LanguagePairTO languagePairTO = findLanguagePair.byId(translation.getLanguagePairId().toString());


        AnswerTO answerTO = checkPracticeAnswer(practiceWordCheckTO, translationPractice, translation, languagePairTO);
        return answerTO;

    }


    private AnswerTO checkPracticeAnswer(PracticeWordCheckTO practiceWordCheckTO, TranslationPractice translationPractice,
                                         Translation translation,
                                         LanguagePairTO languagePairTO) {

        AnswerTO.Builder answerTOBuilder = AnswerTO.newBuilder()
                .withLanguagePairId(languagePairTO.id())
                .withUserId(practiceWordCheckTO.userId())
                .withTranslationId(practiceWordCheckTO.translationId())
                .withGivenAnswer(practiceWordCheckTO.answerGiven());

        if (practiceWordCheckTO.answerOrderType() == NORMAL) {
            if (answerChecker.checkFor(translation.getLanguageB(), practiceWordCheckTO.answerGiven(), practiceWordCheckTO.getNormalized())) {
                translationPractice.answerCorrect();
                updateLanguagePairHealth.updateCorrect(languagePairTO.id());
                answerTOBuilder.withCorrectAnswer(true)
                        .withExpectedAnswers(translation.getLanguageB())
                        .withQuestion(practiceWordCheckTO.questionGiven());

            } else {
                translationPractice.answerIncorrect();
                updateLanguagePairHealth.updateIncorrect(languagePairTO.id());
                answerTOBuilder.withCorrectAnswer(false)
                        .withExpectedAnswers(translation.getLanguageB())
                        .withQuestion(practiceWordCheckTO.questionGiven());

            }

        } else if (practiceWordCheckTO.answerOrderType() == REVERSE) {
            if (answerChecker.checkFor(translation.getLanguageA(), practiceWordCheckTO.answerGiven(), practiceWordCheckTO.getNormalized())) {
                translationPractice.answerCorrectReverse();
                updateLanguagePairHealth.updateCorrect(languagePairTO.id());

                answerTOBuilder.withCorrectAnswer(true)
                        .withExpectedAnswers(translation.getLanguageA())
                        .withQuestion(practiceWordCheckTO.questionGiven());

            } else {
                translationPractice.answerIncorrectReverse();
                updateLanguagePairHealth.updateIncorrect(languagePairTO.id());

                answerTOBuilder.withCorrectAnswer(false)
                        .withExpectedAnswers(translation.getLanguageA())
                        .withQuestion(practiceWordCheckTO.questionGiven());

            }

        }

         translationPracticeRepository.save(translationPractice);


        AnswerTO answerTO = answerTOBuilder.build();
        journalPracticeResults.forResult(answerTO, Optional.ofNullable(practiceWordCheckTO.lessonId()));

        return answerTO;
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
