package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import org.springframework.stereotype.Component;

@Component
public class LanguagePairResponseMapper implements ResponseMapper<LanguagePairTO, LanguagePairResponse> {
    @Override
    public LanguagePairResponse map(LanguagePairTO languagePairTO) {
        return LanguagePairResponse.builder()
                .id(languagePairTO.id())
                .languageA(languagePairTO.languageFrom())
                .languageB(languagePairTO.languageTo())
                .build();
    }
}
