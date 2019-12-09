package be.artisjaap.polyglot.web.endpoints.request;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewLanguagePairRequest {
    private String languageA;
    private String languageB;

}
