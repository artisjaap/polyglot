package be.artisjaap.polyglot.core.action.to;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class JournalFilterTO {

    private String userId;
    private String languagePairId;
    private String lessonId;
    private LocalDateTime from;
    private LocalDateTime until;
    @Builder.Default
    private Boolean errorsOnly = false;


}
