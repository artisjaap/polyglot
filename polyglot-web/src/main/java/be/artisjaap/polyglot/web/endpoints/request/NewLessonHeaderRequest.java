package be.artisjaap.polyglot.web.endpoints.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewLessonHeaderRequest {
    private String languagePairId;
    private String name;
}
