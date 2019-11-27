package be.artisjaap.polyglot.web.endpoints.response;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationResponse {
    private String id;
    private String languageA;
    private String languageB;
}
