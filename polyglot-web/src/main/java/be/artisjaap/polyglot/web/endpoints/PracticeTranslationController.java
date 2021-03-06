package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.core.utils.WebUtils;
import be.artisjaap.polyglot.core.action.journal.JournalPracticeResults;
import be.artisjaap.polyglot.core.action.lesson.FindPracticeWords;
import be.artisjaap.polyglot.core.action.practice.CreatePracticePdf;
import be.artisjaap.polyglot.core.action.to.AnswerTO;
import be.artisjaap.polyglot.core.action.to.CreatePracticePdfTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.action.to.PracticeWordCheckTO;
import be.artisjaap.polyglot.web.endpoints.old.response.LanguagePracticeJournalResponse;
import be.artisjaap.polyglot.web.endpoints.old.response.TranslationJournalResponse;
import be.artisjaap.polyglot.web.endpoints.request.CreatePracticePdfRequest;
import be.artisjaap.polyglot.web.endpoints.request.PracticeAnswerValidateRequest;
import be.artisjaap.polyglot.web.endpoints.response.PracticeAnswerResponse;
import be.artisjaap.polyglot.web.endpoints.response.PracticeAnswerResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PracticeTranslationController extends BaseController {
    @Resource
    private FindPracticeWords findPracticeWords;

    @Resource
    private PracticeAnswerResponseMapper practiceAnswerResponseMapper;

    @Resource
    private CreatePracticePdf createPracticePdf;

    @Resource
    private JournalPracticeResults journalPracticeResults;

    @RequestMapping(value = "/practice/validate", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<PracticeAnswerResponse> createLanguagePair(@RequestBody PracticeAnswerValidateRequest practiceAnswerValidateRequest) {
        AnswerTO answerTO = findPracticeWords.checkAnswer(PracticeWordCheckTO.newBuilder()
                .withAnswerGiven(practiceAnswerValidateRequest.getAnswerGiven())
                .withNextOrderType(practiceAnswerValidateRequest.getAnswerOrderType())
                .withTranslationId(practiceAnswerValidateRequest.getTranslationId())
                .withUserId(userId())
                .withAnswerOrderType(practiceAnswerValidateRequest.getAnswerOrderType())
                .withLessonId(practiceAnswerValidateRequest.getLessonId())
                .build());

        return ResponseEntity.ok(practiceAnswerResponseMapper.map(answerTO));
    }


    @RequestMapping(value = "/practice/generate-pdf", method = RequestMethod.POST)
    public
    void generatePracticePdf(HttpServletResponse response, @RequestBody CreatePracticePdfRequest generatePracticePdfRequest) throws IOException {
        Optional<byte[]> generatedPdf = createPracticePdf.forData(CreatePracticePdfTO.builder()
                .languagePairId(generatePracticePdfRequest.getLanguagePairId())
                .lessonId(generatePracticePdfRequest.getLessonId())
                .numberOfWords(generatePracticePdfRequest.getNumberOfWords())
                .reversed(generatePracticePdfRequest.getReversed())
                .build());


        OutputStream outputStream = WebUtils.maakOutputstreamVoorPdfFile(response, "oefen-les");
        outputStream.write(generatedPdf.orElse(null));

        response.flushBuffer();
    }

    @RequestMapping(value = "/practice/results/current-month/{lessonId}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LanguagePracticeJournalResponse> practiceResultsForCurrentMonth(String lessonId) throws IOException {
        journalPracticeResults.findJournalFor(userId(), lessonId, LocalDateUtils.nowYearMonth());
        return ResponseEntity.ok(null);
    }


}
