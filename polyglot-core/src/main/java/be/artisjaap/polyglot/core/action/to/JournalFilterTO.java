package be.artisjaap.polyglot.core.action.to;

import java.time.LocalDate;

public class JournalFilterTO {

    private String userId;
    private String languagePairId;
    private String lessonId;
    private LocalDate from;
    private LocalDate until;

    private JournalFilterTO(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        lessonId = builder.lessonId;
        from = builder.from;
        until = builder.until;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(JournalFilterTO copy) {
        Builder builder = new Builder();
        builder.userId = copy.userId;
        builder.languagePairId = copy.languagePairId;
        builder.lessonId = copy.lessonId;
        builder.from = copy.from;
        builder.until = copy.until;
        return builder;
    }

    public String userId() {
        return userId;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public String lessonId() {
        return lessonId;
    }

    public LocalDate from() {
        return from;
    }

    public LocalDate until() {
        return until;
    }

    public static final class Builder {
        private String userId;
        private String languagePairId;
        private String lessonId;
        private LocalDate from;
        private LocalDate until;

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

        public Builder withLessonId(String val) {
            lessonId = val;
            return this;
        }

        public Builder withFrom(LocalDate val) {
            from = val;
            return this;
        }

        public Builder withUntil(LocalDate val) {
            until = val;
            return this;
        }

        public JournalFilterTO build() {
            return new JournalFilterTO(this);
        }
    }
}
