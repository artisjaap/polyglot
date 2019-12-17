package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.TranslationTO;
import org.springframework.stereotype.Component;

@Component
public class TranslationResponseMapper  implements ResponseMapper<TranslationTO, TranslationResponse> {
    @Override
    public TranslationResponse map(TranslationTO translationTO) {
        return TranslationResponse.builder()
                .id(translationTO.id())
                .languageA(translationTO.languageA())
                .languageB(translationTO.languageB())
                .build();
    }
}
