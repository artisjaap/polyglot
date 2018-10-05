package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.core.model.ReferenceableTO;

import java.util.List;

public class LessonTO extends ReferenceableTO {
    private String name;
    private String languagePairId;
    private List<LessonTranslationPairTO> translations;
    private String userId;

    private LessonTO(Builder builder) {
        buildCommon(builder);
        name = builder.name;
        languagePairId = builder.languagePairId;
        translations = builder.translations;
        userId = builder.userId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String name() {
        return name;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public List<LessonTranslationPairTO> translations() {
        return translations;
    }

    public String userId() {
        return userId;
    }

    public static final class Builder extends AbstractBuilder<Builder> {
        private String name;
        private String languagePairId;
        private List<LessonTranslationPairTO> translations;
        private String userId;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withTranslations(List<LessonTranslationPairTO> val) {
            translations = val;
            return this;
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public LessonTO build() {
            return new LessonTO(this);
        }
    }
}
