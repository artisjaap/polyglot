package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.TranslationsForUserTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TranslationsLoadedByFileResponseMapper implements ResponseMapper<TranslationsForUserTO, TranslationsLoadedByFileResponse> {

    @Resource
    private TranslationResponseMapper lessonTranslationPairResponseMapper;

    @Override
    public TranslationsLoadedByFileResponse map(TranslationsForUserTO translationsForUserTO) {
        return TranslationsLoadedByFileResponse.builder()
                .languagePairId(translationsForUserTO.languagePairId())
                .translations(lessonTranslationPairResponseMapper.mapToResponse(translationsForUserTO.translations()))
                .build();
    }
}
