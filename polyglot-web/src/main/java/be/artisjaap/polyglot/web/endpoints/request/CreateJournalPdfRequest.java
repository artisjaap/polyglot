package be.artisjaap.polyglot.web.endpoints.request;

import be.artisjaap.common.utils.LocalDateUtils;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
public class CreateJournalPdfRequest {
    @NonNull
    private String languagePairId;

    private String lessonId;

    @NonNull
    @Builder.Default
    private LocalDate from = LocalDateUtils.MIN_DATE;

    @NonNull
    @Builder.Default
    private LocalDate to = LocalDateUtils.MAX_DATE;

}
