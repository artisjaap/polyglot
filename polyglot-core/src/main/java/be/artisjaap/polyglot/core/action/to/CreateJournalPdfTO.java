package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.common.utils.LocalDateUtils;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime from = LocalDateUtils.MIN_DATE.atStartOfDay();

    @NonNull
    @Builder.Default
    private LocalDateTime to = LocalDateUtils.MAX_DATE.atStartOfDay();
}
