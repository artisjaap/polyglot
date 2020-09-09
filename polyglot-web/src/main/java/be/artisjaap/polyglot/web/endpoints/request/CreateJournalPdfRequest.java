package be.artisjaap.polyglot.web.endpoints.request;

import be.artisjaap.common.utils.LocalDateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateJournalPdfRequest {

    private String languagePairId;

    private String lessonId;

    private LocalDate from = LocalDateUtils.MIN_DATE;

    private LocalDate to = LocalDateUtils.MAX_DATE;

    public CreateJournalPdfRequest(){}
}
