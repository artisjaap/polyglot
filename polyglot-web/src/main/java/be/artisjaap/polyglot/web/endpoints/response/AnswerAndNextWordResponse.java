package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.AnswerAndNextWordTO;

public class AnswerAndNextWordResponse {
    private PracticeWordResponse practiceWordResponse;
    private AnswerResponse answerResponse;

    private AnswerAndNextWordResponse(Builder builder) {
        practiceWordResponse = builder.practiceWordResponse;
        answerResponse = builder.answerResponse;
    }


    public static AnswerAndNextWordResponse from(AnswerAndNextWordTO answerAndNextWordTO) {
        return newBuilder()
                .withAnswerResponse(AnswerResponse.from(answerAndNextWordTO.answer()))
                .withPracticeWordResponse(PracticeWordResponse.from(answerAndNextWordTO.practiceWord()))
                .build();
    }

    public PracticeWordResponse getPracticeWordResponse() {
        return practiceWordResponse;
    }

    public AnswerResponse getAnswerResponse() {
        return answerResponse;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private PracticeWordResponse practiceWordResponse;
        private AnswerResponse answerResponse;

        private Builder() {
        }

        public Builder withPracticeWordResponse(PracticeWordResponse val) {
            practiceWordResponse = val;
            return this;
        }

        public Builder withAnswerResponse(AnswerResponse val) {
            answerResponse = val;
            return this;
        }

        public AnswerAndNextWordResponse build() {
            return new AnswerAndNextWordResponse(this);
        }
    }
}
