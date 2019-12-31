package be.artisjaap.i18n.action.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewTranslationTO {
    private String key;
    private String translation;
    private String languageIsoCode;
    private String bundleName;


}
