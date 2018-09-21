package be.artisjaap.polyglot.core.action.to.test;

public class CreateTestTO {
    private String userId;
    private String lessonId;
    private OrderType orderType;

    private CreateTestTO(Builder builder) {
        userId = builder.userId;
        lessonId = builder.lessonId;
        orderType = builder.orderType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String userId() {
        return userId;
    }

    public String lessonId() {
        return lessonId;
    }

    public OrderType orderType() {
        return orderType;
    }


    public static final class Builder {
        private String userId;
        private String lessonId;
        private OrderType orderType;

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withLessonId(String val) {
            lessonId = val;
            return this;
        }

        public Builder withOrderType(OrderType val) {
            orderType = val;
            return this;
        }

        public CreateTestTO build() {
            return new CreateTestTO(this);
        }
    }
}
