package be.artisjaap.polyglot.web.endpoints.request;

import java.util.List;

public class TestSolutionRequest {
    private String lessonId;
    private List<WordSolutionRequest> solutions;

    private TestSolutionRequest(Builder builder) {
        lessonId = builder.lessonId;
        solutions = builder.solutions;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getLessonId() {
        return lessonId;
    }

    public List<WordSolutionRequest> getSolutions() {
        return solutions;
    }

    public static final class Builder {
        private String lessonId;
        private List<WordSolutionRequest> solutions;

        private Builder() {
        }

        public Builder withLessonId(String val) {
            lessonId = val;
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
