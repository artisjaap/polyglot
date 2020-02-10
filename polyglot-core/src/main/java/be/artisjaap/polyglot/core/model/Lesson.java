package be.artisjaap.polyglot.core.model;

import be.artisjaap.common.model.AbstractDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "PolLesson")
public class Lesson extends AbstractDocument {

    private String name;
    private ObjectId languagePairId;
    private Set<ObjectId> translations;
    private Set<String> tags;
    private ObjectId userId;

    private Lesson() {
    }

    private Lesson(Builder builder) {
        buildCommon(builder);
        name = builder.name;
        languagePairId = builder.languagePairId;
        translations = builder.translations;
        userId = builder.userId;
        tags = builder.tags;
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

    public Set<ObjectId> getTranslations() {
        return translations;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void addTranslations(Set<ObjectId> translationIds) {
        translations.addAll(translationIds);
    }

    public void removeTranslations(Set<ObjectId> translationIds) {
        translations.removeAll(translationIds);
    }

    public void addTranslation(ObjectId translationId) {
        translations.add(translationId);
    }

    public void removeTranslation(ObjectId translationId) {
        translations.remove(translationId);
    }

    public static final class Builder extends AbstractBuilder<Builder> {
        private String name;
        private ObjectId languagePairId;
        private Set<ObjectId> translations;
        private Set<String> tags;
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

        public Builder withTranslations(Set<ObjectId> val) {
            translations = val;
            return this;
        }

        public Builder withUserId(ObjectId val) {
            userId = val;
            return this;
        }
        public Builder withTags(Set<String> val) {
            tags = val;
            return this;
        }
        public Lesson build() {
            return new Lesson(this);
        }
    }
}
