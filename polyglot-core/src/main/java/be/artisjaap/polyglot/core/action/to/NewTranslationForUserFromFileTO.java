package be.artisjaap.polyglot.core.action.to;

import java.io.Reader;

public class NewTranslationForUserFromFileTO {
    private Reader reader;
    private String userId;
    private String languagePairId;

    private NewTranslationForUserFromFileTO(Builder builder) {
        reader = builder.reader;
        userId = builder.userId;
        languagePairId = builder.languagePairId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Reader reader() {
        return reader;
    }

    public String userId() {
        return userId;
    }

    public String languagePairId() {
        return languagePairId;
    }


    public static final class Builder {
        private Reader reader;
        private String userId;
        private String languagePairId;

        private Builder() {
        }

        public Builder withReader(Reader val) {
            reader = val;
            return this;
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public NewTranslationForUserFromFileTO build() {
            return new NewTranslationForUserFromFileTO(this);
        }
    }
}
