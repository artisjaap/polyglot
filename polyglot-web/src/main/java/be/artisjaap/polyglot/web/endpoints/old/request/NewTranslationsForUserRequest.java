package be.artisjaap.polyglot.web.endpoints.old.request;

import java.util.ArrayList;
import java.util.List;

public class NewTranslationsForUserRequest {
    private String userId;
    private String languagePairId;
    private List<NewSimpleTranslationRequest> translations;

    private NewTranslationsForUserRequest(){}

    private NewTranslationsForUserRequest(Builder builder) {
        setUserId(builder.userId);
        setLanguagePairId(builder.languagePairId);
        setTranslations(builder.translations);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLanguagePairId() {
        return languagePairId;
    }

    public void setLanguagePairId(String languagePairId) {
        this.languagePairId = languagePairId;
    }

    public List<NewSimpleTranslationRequest> getTranslations() {
        return translations;
    }

    public void setTranslations(List<NewSimpleTranslationRequest> translations) {
        this.translations = translations;
    }

    public static final class Builder {
        private String userId;
        private String languagePairId;
        private List<NewSimpleTranslationRequest> translations = new ArrayList<>();

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withTranslations(List<NewSimpleTranslationRequest> val) {
            translations = val;
            return this;
        }

        public NewTranslationsForUserRequest build() {
            return new NewTranslationsForUserRequest(this);
        }
    }
}
