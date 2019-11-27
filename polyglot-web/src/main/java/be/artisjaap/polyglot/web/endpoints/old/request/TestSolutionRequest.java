package be.artisjaap.polyglot.web.endpoints.old.request;

import java.util.List;

public class TestSolutionRequest {
    private String lessonId;
    private String userId;
    private List<WordSolutionRequest> solutions;

    private TestSolutionRequest(Builder builder) {
        lessonId = builder.lessonId;
        userId = builder.userId;
        solutions = builder.solutions;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getUserId() {
        return userId;
    }

    public List<WordSolutionRequest> getSolutions() {
        return solutions;
    }

    public static final class Builder {
        private String lessonId;
        private String userId;
        private List<WordSolutionRequest> solutions;

        private Builder() {
        }

        public Builder withLessonId(String val) {
            lessonId = val;
            return this;
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withSolutions(List<WordSolutionRequest> val) {
            solutions = val;
            return this;
        }

        public TestSolutionRequest build() {
            return new TestSolutionRequest(this);
        }
    }
}
