package be.artisjaap.polyglot.core.action.to;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class LanguagePracticeMistakesReportTO {
    private String userId;
    private String languagePairId;
    private String lessonId;
    private LocalDateTime from;
    private LocalDateTime until;
}
