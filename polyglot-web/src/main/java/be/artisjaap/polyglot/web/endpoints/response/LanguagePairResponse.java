package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.web.endpoints.old.response.AbstractReferenceableResponse;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanguagePairResponse extends AbstractReferenceableResponse {
    private String id;
    private String languageA;
    private String languageB;
}
