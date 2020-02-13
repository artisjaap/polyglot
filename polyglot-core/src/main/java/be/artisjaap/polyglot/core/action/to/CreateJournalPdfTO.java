package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.common.utils.LocalDateUtils;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Builder
@Data
public class CreateJournalPdfTO {
    @NonNull
    private String userId;

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
