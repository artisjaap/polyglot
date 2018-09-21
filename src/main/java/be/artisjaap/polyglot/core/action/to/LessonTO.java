package be.artisjaap.polyglot.core.action.to;

import org.bson.types.ObjectId;

import java.util.List;

public class LessonTO extends ReferenceableTO {
    private String name;
    private String languagePairId;
    private List<String> translations;
    private ObjectId userId;

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

    public List<String> translations() {
        return translations;
    }

    public ObjectId userId() {
        return userId;
    }

    public static final class Builder extends AbstractBuilder<Builder> {
        private String name;
        private String languagePairId;
        private List<String> translations;
        private ObjectId userId;

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

        public Builder withTranslations(List<String> val) {
            translations = val;
            return this;
        }

        public Builder withUserId(ObjectId val) {
            userId = val;
            return this;
        }

        public LessonTO build() {
            return new LessonTO(this);
        }
    }
}
