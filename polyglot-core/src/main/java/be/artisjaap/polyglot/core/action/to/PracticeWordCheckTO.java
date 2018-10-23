package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.polyglot.core.action.to.test.OrderType;

public class PracticeWordCheckTO {
    private String userId;
    private String translationId;
    private String answerGiven;
    private OrderType answerOrderType;
    private OrderType nextOrderType;

    private PracticeWordCheckTO(Builder builder) {
        userId = builder.userId;
        translationId = builder.translationId;
        answerGiven = builder.answerGiven;
        answerOrderType = builder.answerOrderType;
        nextOrderType = builder.nextOrderType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String userId() {
        return userId;
    }

    public String translationId() {
        return translationId;
    }

    public String answerGiven() {
        return answerGiven;
    }

    public OrderType answerOrderType() {
        return answerOrderType;
    }

    public OrderType nextOrderType() {
        return nextOrderType;
    }


    public static final class Builder {
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

        public PracticeWordCheckTO build() {
            return new PracticeWordCheckTO(this);
        }
    }
}
