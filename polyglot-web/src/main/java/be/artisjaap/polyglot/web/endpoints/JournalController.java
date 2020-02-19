package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.core.utils.WebUtils;
import be.artisjaap.polyglot.core.action.journal.CreateJournalPdf;
import be.artisjaap.polyglot.core.action.to.CreateJournalPdfTO;
import be.artisjaap.polyglot.web.endpoints.request.CreateJournalPdfRequest;
import be.artisjaap.polyglot.web.security.SecurityUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JournalController {

    private CreateJournalPdf createJournalPdf;

    @RequestMapping(value = "/journal/generate-pdf", method = RequestMethod.POST)
    public @ResponseBody
    void generatePracticePdf(HttpServletResponse response, @RequestBody CreateJournalPdfRequest createJournalPdfRequest) throws IOException {
        Optional<byte[]> generatedPdf = createJournalPdf.forData(CreateJournalPdfTO.builder()
                .languagePairId(createJournalPdfRequest.getLanguagePairId())
                .lessonId(createJournalPdfRequest.getLessonId())
                .from(createJournalPdfRequest.getFrom().atStartOfDay())
                .to(LocalDateUtils.endOfDay(createJournalPdfRequest.getTo()))
                .userId(SecurityUtils.userId())
                .build());


        OutputStream outputStream = WebUtils.maakOutputstreamVoorPdfFile(response, "journal.pdf");
        outputStream.write(generatedPdf.orElse(null));

        response.flushBuffer();
    }
}
