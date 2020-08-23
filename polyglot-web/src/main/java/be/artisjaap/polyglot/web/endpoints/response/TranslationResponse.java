package be.artisjaap.polyglot.web.endpoints.response;


import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationResponse {
    private String id;
    private Set<String> languageA;
    private Set<String> languageB;
}
