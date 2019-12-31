package be.artisjaap.polyglot.web.endpoints.response;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class LessonResponse {
    private String id;
    private String title;
    private String languagePairId;
    @Builder.Default
    private List<TranslationResponse> translations = new ArrayList<>();

}
