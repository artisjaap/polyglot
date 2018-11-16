package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.TestResultAssembler;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.test.*;
import be.artisjaap.polyglot.core.model.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class TestForLesson {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private PracticeWords practiceWords;

    @Autowired
    private LanguagePairRepository languagePairRepository;

    @Autowired
    private TestResultAssembler testResultAssembler;

    @Autowired
    private TestResultRepository testResultRepository;


    public TestAssignmentTO asSimpleTestForLesson(CreateTestTO createTestTO) {
        Lesson lesson = lessonRepository.findById(new ObjectId(createTestTO.lessonId())).orElseThrow(() -> new IllegalStateException(""));
        LanguagePair languagePair = languagePairRepository.findById(lesson.getLanguagePairId()).orElseThrow(() -> new IllegalStateException(""));
        List<String> translationIds = lesson.getTranslations().stream().map(ObjectId::toString).collect(Collectors.toList());

        switch (createTestTO.orderType()) {
            case NORMAL:
                return normalOrderTest( lesson, translationIds);
            case RANDOM:
                return randomOrderTest( lesson,  translationIds);
            case REVERSE:
                return reverseOrderTest( lesson, translationIds);

        }

        throw new UnsupportedOperationException("");


    }

    private TestAssignmentTO randomOrderTest( Lesson lesson, List<String> translationIds) {

        List<String> part1 = translationIds.subList(0, translationIds.size() / 2);
        List<String> part2 = translationIds.subList(translationIds.size() / 2, translationIds.size());

        List<PracticeWordTO> practiceWordTOS = practiceWords.givePracticeWordsForTranslations(OrderType.NORMAL, part1);
        practiceWordTOS.addAll(practiceWords.givePracticeWordsForTranslations(OrderType.REVERSE, part2));

        return TestAssignmentTO.newBuilder()
                .withLessonName(lesson.getName())
                .withLessonId(lesson.getId().toString())
                .withWords(practiceWordTOS)
                .build();
    }


    private TestAssignmentTO reverseOrderTest( Lesson lesson, List<String> translationIds) {
        List<PracticeWordTO> practiceWordTOS = practiceWords.givePracticeWordsForTranslations( OrderType.REVERSE, translationIds);

        return TestAssignmentTO.newBuilder()
                .withLessonName(lesson.getName())
                .withLessonId(lesson.getId().toString())
                .withWords(practiceWordTOS)
                .build();
    }

    private TestAssignmentTO normalOrderTest(Lesson lesson, List<String> translationIds) {

        List<PracticeWordTO> practiceWordTOS = practiceWords.givePracticeWordsForTranslations( OrderType.NORMAL, translationIds);

        return TestAssignmentTO.newBuilder()
                .withLessonName(lesson.getName())
                .withLessonId(lesson.getId().toString())
                .withWords(practiceWordTOS)
                .build();
    }

    public TestResultTO correctTest(TestSolutionTO testSolutionTO) {
        Lesson lesson = lessonRepository.findById(new ObjectId(testSolutionTO.lessonId())).orElseThrow(() -> new IllegalStateException(""));
        List<Translation> translations = StreamSupport.stream(translationRepository.findAllById(lesson.getTranslations()).spliterator(), false).collect(Collectors.toList());

        LanguagePair languagePair = languagePairRepository.findById(lesson.getLanguagePairId()).orElseThrow(() -> new IllegalStateException(""));

        Map<String, WordSolutionTO> collect = testSolutionTO.solutions().stream().collect(Collectors.toMap(WordSolutionTO::translationId, Function.identity()));

        List<WordResultTO> results = translations.stream().map(translation -> {
            WordSolutionTO wordSolutionTO = collect.get(translation.getId().toString());

            String expectedAnswer = languagePair.getLanguageFrom().equals(wordSolutionTO.answerLanguage()) ?
                    translation.getLanguageA() : translation.getLanguageB();


            boolean isCorrect = expectedAnswer.equalsIgnoreCase(wordSolutionTO.answer());

            return WordResultTO.newBuilder()
                    .withTranslationId(translation.getId().toString())
                    .withQuestion(wordSolutionTO.question())
                    .withGivenAnswer(wordSolutionTO.answer())
                    .withExpectedAnswer(expectedAnswer)
                    .withCorrect(isCorrect)
                    .build();


        }).collect(Collectors.toList());


        TestResultTO testResult = TestResultTO.newBuilder()
                .withLessonName(lesson.getName())
                .withUserId(testSolutionTO.userId())
                .withLessonId(testSolutionTO.lessonId())
                .withWordResults(results)
                .withScore(Long.valueOf(results.stream().filter(WordResultTO::correct).count()).intValue())
                .build();

        saveTestResult(testResult);

        return testResult;
    }

    private void saveTestResult(TestResultTO testResultTO) {
        TestResult testResult = testResultAssembler.assembleEntity(testResultTO);
        testResultRepository.save(testResult);
    }
}
