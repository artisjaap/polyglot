package be.artisjaap.polyglot.web.endpoints.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePracticePdfRequest {
    private String languagePairId;
    private String lessonId;
    private Integer numberOfWords;
}
