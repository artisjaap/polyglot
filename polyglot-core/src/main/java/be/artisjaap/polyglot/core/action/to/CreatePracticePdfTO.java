package be.artisjaap.polyglot.core.action.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatePracticePdfTO {
    private String languagePairId;
    private String lessonId;
    private Integer numberOfWords;
}
