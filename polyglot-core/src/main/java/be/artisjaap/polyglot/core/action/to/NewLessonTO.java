package be.artisjaap.polyglot.core.action.to;

import java.util.ArrayList;
import java.util.List;


public class NewLessonTO {
    private String name;
    private String userId;
    private String languagePairId;
    private List<String> translationsIds;


    private NewLessonTO(Builder builder) {
        name = builder.name;
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        translationsIds = builder.translationsIds;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String userId() {
        return userId;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public String name() {
        return name;
    }

    public List<String> translationsIds() {
        return translationsIds;
    }

    public static final class Builder {
        private String name;
        private String userId;
        private String languagePairId;
        private List<String> translationsIds = new ArrayList<>();

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

        public Builder withTranslationsIds(List<String> val) {
            translationsIds = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public NewLessonTO build() {
            return new NewLessonTO(this);
        }
    }
}
