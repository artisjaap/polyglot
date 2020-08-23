package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.polyglot.core.action.to.TranslationTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TranslationResponse {
    private String id;
    private String languagePairId;
    private Set<String> languageA;
    private Set<String> languageB;



    private TranslationResponse(Builder builder) {
        languagePairId = builder.languagePairId;
        languageA = builder.languageA;
        languageB = builder.languageB;
        id = builder.id;
    }

    public static TranslationResponse from(TranslationTO t){
        return newBuilder()
                .withLanguageA(t.languageA())
                .withLanguageB(t.languageB())
                .withLanguagePairId(t.languagePairId())
                .withId(t.id())
                .build();
    }

    public static List<TranslationResponse> from(List<TranslationTO> translations){
        return translations.stream().map(TranslationResponse::from).collect(Collectors.toList());
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getLanguagePairId() {
        return languagePairId;
    }

    public Set<String> getLanguageA() {
        return languageA;
    }

    public Set<String> getLanguageB() {
        return languageB;
    }

    public String getId() {
        return id;
    }

    public static final class Builder {
        private String languagePairId;
        private Set<String> languageA;
        private Set<String> languageB;
        private String id;

        private Builder() {
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withLanguageA(Set<String> val) {
            languageA = val;
            return this;
        }

        public Builder withLanguageB(Set<String> val) {
            languageB = val;
            return this;
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public TranslationResponse build() {
            return new TranslationResponse(this);
        }
    }
}
