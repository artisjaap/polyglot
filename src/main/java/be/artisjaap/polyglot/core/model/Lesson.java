package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;

import java.util.List;

public class Lesson extends AbstractDocument{

    private String name;
    private ObjectId languagePairId;
    private List<ObjectId> translations;
    private ObjectId userId;

    private Lesson() {
    }

    private Lesson(Builder builder) {
        buildCommon(builder);
        name = builder.name;
        languagePairId = builder.languagePairId;
        translations = builder.translations;
        userId = builder.userId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public ObjectId getLanguagePairId() {
        return languagePairId;
    }

    public List<ObjectId> getTranslations() {
        return translations;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public static final class Builder extends AbstractBuilder<Builder> {
        private String name;
        private ObjectId languagePairId;
        private List<ObjectId> translations;
        private ObjectId userId;

        private Builder() {
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withLanguagePairId(ObjectId val) {
            languagePairId = val;
            return this;
        }

        public Builder withTranslations(List<ObjectId> val) {
            translations = val;
            return this;
        }

        public Builder withUserId(ObjectId val) {
            userId = val;
            return this;
        }

        public Lesson build() {
            return new Lesson(this);
        }
    }
}
