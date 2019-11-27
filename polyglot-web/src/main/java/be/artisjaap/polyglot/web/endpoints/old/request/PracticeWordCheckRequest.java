package be.artisjaap.polyglot.web.endpoints.old.request;

import be.artisjaap.polyglot.core.action.to.test.OrderType;

public class PracticeWordCheckRequest {
    private String lessonId;
    private String userId;
    private String translationId;
    private String answerGiven;
    private OrderType answerOrderType;
    private OrderType nextOrderType;

    private PracticeWordCheckRequest(){}

    private PracticeWordCheckRequest(Builder builder) {
        lessonId = builder.lessonId;
        userId = builder.userId;
        translationId = builder.translationId;
        answerGiven = builder.answerGiven;
        answerOrderType = builder.answerOrderType;
        nextOrderType = builder.nextOrderType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUserId() {
        return userId;
    }

    public String getTranslationId() {
        return translationId;
    }

    public String getAnswerGiven() {
        return answerGiven;
    }

    public OrderType getAnswerOrderType() {
        return answerOrderType;
    }

    public OrderType getNextOrderType() {
        return nextOrderType;
    }

    public String getLessonId() {
        return lessonId;
    }

    public static final class Builder {
        private String lessonId;
        private String userId;
        private String translationId;
        private String answerGiven;
        private OrderType answerOrderType;
        private OrderType nextOrderType;

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withTranslationId(String val) {
            translationId = val;
            return this;
        }

        public Builder withAnswerGiven(String val) {
            answerGiven = val;
            return this;
        }

        public Builder withAnswerOrderType(OrderType val) {
            answerOrderType = val;
            return this;
        }

        public Builder withNextOrderType(OrderType val) {
            nextOrderType = val;
            return this;
        }

        public Builder withLessonId(String val) {
            lessonId = val;
            return this;
        }

        public PracticeWordCheckRequest build() {
            return new PracticeWordCheckRequest(this);
        }
    }
}
