package be.artisjaap.polyglot.web.endpoints.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationsLoadedByFileResponse {
    @NonNull
    private String languagePairId;
    private LessonHeaderResponse lessonHeaderResponse;
    @Builder.Default
    private List<TranslationResponse> translations = new ArrayList<>();
}
