package be.artisjaap.i18n.endpoints.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class I18nTranslationResponse {

    private String key;
    private String value;
}
