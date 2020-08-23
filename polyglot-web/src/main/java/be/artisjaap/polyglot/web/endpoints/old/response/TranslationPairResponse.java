package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.polyglot.core.action.to.TranslationPairTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TranslationPairResponse {
    private String id;
    private Set<String> languageA;
    private Set<String> languageB;

    public static TranslationPairResponse from(TranslationPairTO translationPairTO){
        return TranslationPairResponse.newBuilder()
                .withId(translationPairTO.id())
                .withLanguageA(translationPairTO.languageA())
                .withLanguageB(translationPairTO.languageB())
                .build();
    }
    public static List<TranslationPairResponse> from(List<TranslationPairTO> translationPairTO){
        return translationPairTO.stream().map(TranslationPairResponse::from).collect(Collectors.toList());
    }



    private TranslationPairResponse(Builder builder) {
        id = builder.id;
        languageA = builder.languageA;
        languageB = builder.languageB;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public Set<String> getLanguageA() {
        return languageA;
    }

    public Set<String> getLanguageB() {
        return languageB;
    }

    public static final class Builder {
        private String id;
        private Set<String> languageA;
        private Set<String> languageB;

        private Builder() {
        }

        public Builder withId(String val) {
            id = val;
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

        public TranslationPairResponse build() {
            return new TranslationPairResponse(this);
        }
    }
}
