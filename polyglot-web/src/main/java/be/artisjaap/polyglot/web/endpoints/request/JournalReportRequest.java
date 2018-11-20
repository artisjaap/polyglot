package be.artisjaap.polyglot.web.endpoints.request;

public class JournalReportRequest {
    private String userId;
    private String languagePairId;
    private String lessonId;
    private String from;
    private String until;

    private JournalReportRequest(){}

    private JournalReportRequest(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        lessonId = builder.lessonId;
        from = builder.from;
        until = builder.until;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUserId() {
        return userId;
    }

    public String getLanguagePairId() {
        return languagePairId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getFrom() {
        return from;
    }

    public String getUntil() {
        return until;
    }

    public static final class Builder {
        private String userId;
        private String languagePairId;
        private String lessonId;
        private String from;
        private String until;

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

        public Builder withFrom(String val) {
            from = val;
            return this;
        }

        public Builder withUntil(String val) {
            until = val;
            return this;
        }

        public JournalReportRequest build() {
            return new JournalReportRequest(this);
        }
    }
}
