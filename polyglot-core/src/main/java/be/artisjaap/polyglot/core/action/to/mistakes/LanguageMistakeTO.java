package be.artisjaap.polyglot.core.action.to.mistakes;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class LanguageMistakeTO {

    private String userId;
    private String languagePairId;
    private LocalDateTime from;
    private LocalDateTime until;

    @Builder.Default
    private List<LanguageMistakeDetailTO> mistakeDetails = new ArrayList<>();
}
