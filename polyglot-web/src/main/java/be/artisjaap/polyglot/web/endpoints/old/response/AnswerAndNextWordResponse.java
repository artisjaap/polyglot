package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.polyglot.core.action.to.AnswerAndNextWordTO;

public class AnswerAndNextWordResponse {
    private PracticeWordResponse practiceWordResponse;
    private AnswerResponse answerResponse;
    private LessonPracticeStatusResponse lessonPracticeStatusResponse;

    private AnswerAndNextWordResponse(Builder builder) {
        practiceWordResponse = builder.practiceWordResponse;
        answerResponse = builder.answerResponse;
        lessonPracticeStatusResponse = builder.lessonPracticeStatusResponse;
    }


    public static AnswerAndNextWordResponse from(AnswerAndNextWordTO answerAndNextWordTO) {
        return newBuilder()
                .withAnswerResponse(AnswerResponse.from(answerAndNextWordTO.answer()))
                .withPracticeWordResponse(PracticeWordResponse.from(answerAndNextWordTO.practiceWord()))
                .withLessonPracticeStatusResponse(LessonPracticeStatusResponse.from(answerAndNextWordTO.lessonPracticeStatus()))
                .build();
    }

    public PracticeWordResponse getPracticeWordResponse() {
        return practiceWordResponse;
    }

    public AnswerResponse getAnswerResponse() {
        return answerResponse;
    }
    
    public LessonPracticeStatusResponse getLessonPracticeStatusResponse() {
        return lessonPracticeStatusResponse;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private PracticeWordResponse practiceWordResponse;
        private AnswerResponse answerResponse;
        private LessonPracticeStatusResponse lessonPracticeStatusResponse;

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
        
        
        public Builder withLessonPracticeStatusResponse(LessonPracticeStatusResponse val) {
            lessonPracticeStatusResponse = val;
            return this;
        }
        
        

        public AnswerAndNextWordResponse build() {
            return new AnswerAndNextWordResponse(this);
        }
    }
}
