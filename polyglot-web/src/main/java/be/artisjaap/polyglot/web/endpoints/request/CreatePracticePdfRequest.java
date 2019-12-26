package be.artisjaap.polyglot.web.endpoints.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatePracticePdfRequest {
    private String languagePairId;
    private Integer numberOfWords;
}
