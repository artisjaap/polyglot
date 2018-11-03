package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.TranslationPracticeAssembler;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.core.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    private FindUser findUser;

    @Autowired
    private IntroduceNewWordForPractice introduceNewWordForPractice;

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
    private MergeTranslationPractice mergeTranslationPractice;


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
            if (translation.getLanguageB().equalsIgnoreCase(practiceWordCheckTO.answerGiven())) {
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
            if (translation.getLanguageA().equalsIgnoreCase(practiceWordCheckTO.answerGiven())) {
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
        journalPracticeResults.forResult(answerTO);

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

    public List<PracticeWordTO> givePracticeWordsForTranslations(String userId, OrderType orderType, List<String> translationIds) {
        List<ObjectId> translationIdsObject = translationIds.stream().map(ObjectId::new).collect(Collectors.toList());
        List<Translation> translations = StreamSupport.stream(translationRepository
                .findAllById(translationIdsObject).spliterator(), false).collect(Collectors.toList());

        List<TranslationPractice> translationPractices = translationPracticeRepository.findByUserIdAndTranslationIdIn(new ObjectId(userId), translationIdsObject);

        return mergeTranslationPractice.merge(translationPractices, translations, orderType);
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
