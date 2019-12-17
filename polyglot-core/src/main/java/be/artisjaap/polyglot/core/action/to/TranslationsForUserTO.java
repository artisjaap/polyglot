package be.artisjaap.polyglot.core.action.to;

import java.util.ArrayList;
import java.util.List;

public class TranslationsForUserTO {
    private String userId;
    private String languagePairId;
    private List<TranslationTO> translations;
    private LessonHeaderTO lessonHeader;

    public String userId() {
        return userId;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public LessonHeaderTO lessonHeader() {
        return lessonHeader;
    }

    public void setLessonHeader(LessonHeaderTO lessonHeader){
        this.lessonHeader = lessonHeader;
    }

    public List<TranslationTO> translations() {
        return translations;
    }

    private TranslationsForUserTO(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        translations = builder.translations;
        lessonHeader = builder.lessonHeader;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String userId;
        private String languagePairId;
        private LessonHeaderTO lessonHeader;
        private List<TranslationTO> translations = new ArrayList<>();

        private Builder() {
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withLanguagePairId(String languagePairId) {
            this.languagePairId = languagePairId;
            return this;
        }


        public Builder addTranslations(List<TranslationTO> translations) {
            this.translations.addAll(translations);
            return this;
        }

        public Builder withLessonHeader(LessonHeaderTO lessonHeader) {
            this.lessonHeader = lessonHeader;
            return this;
        }


        public TranslationsForUserTO build() {
            return new TranslationsForUserTO(this);
        }
    }
}
