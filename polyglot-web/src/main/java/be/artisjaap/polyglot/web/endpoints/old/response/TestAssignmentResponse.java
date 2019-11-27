package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.polyglot.core.action.to.test.TestAssignmentTO;

import java.util.List;

public class TestAssignmentResponse {
    private String lessonId;
    private String lessonName;
    private List<PracticeWordResponse> words;

    private TestAssignmentResponse(Builder builder) {
        lessonId = builder.lessonId;
        lessonName = builder.lessonName;
        words = builder.words;
    }

    public static TestAssignmentResponse from(TestAssignmentTO testAssignmentTO) {
        return newBuilder()
                .withLessonId(testAssignmentTO.lessonId())
                .withLessonName(testAssignmentTO.lessonName())
                .withWords(PracticeWordResponse.from(testAssignmentTO.words()))
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public List<PracticeWordResponse> getWords() {
        return words;
    }

    public static final class Builder {
        private String lessonId;
        private String lessonName;
        private List<PracticeWordResponse> words;

        private Builder() {
        }

        public Builder withLessonId(String val) {
            lessonId = val;
            return this;
        }

        public Builder withLessonName(String val) {
            lessonName = val;
            return this;
        }

        public Builder withWords(List<PracticeWordResponse> val) {
            words = val;
            return this;
        }

        public TestAssignmentResponse build() {
            return new TestAssignmentResponse(this);
        }
    }
}
