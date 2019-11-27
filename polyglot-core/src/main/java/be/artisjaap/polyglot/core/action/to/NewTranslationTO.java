package be.artisjaap.polyglot.core.action.to;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class NewTranslationTO {
    @NonNull
    private String userId;
    @NonNull
    private String languagePairId;
    @NonNull
    private String languageFrom;
    @NonNull
    private String languageTo;
}
