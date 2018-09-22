package be.artisjaap.polyglot.core.action.to.test;

import java.util.List;

public class TestSolutionTO {
    private String lessonId;
    private List<WordSolutionTO> solutions;

    private TestSolutionTO(Builder builder) {
        lessonId = builder.lessonId;
        solutions = builder.solutions;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String lessonId() {
        return lessonId;
    }

    public List<WordSolutionTO> solutions() {
        return solutions;
    }


    public static final class Builder {
        private String lessonId;
        private List<WordSolutionTO> solutions;

        private Builder() {
        }

        public Builder withLessonId(String val) {
            lessonId = val;
            return this;
        }

        public Builder withSolutions(List<WordSolutionTO> val) {
            solutions = val;
            return this;
        }

        public TestSolutionTO build() {
            return new TestSolutionTO(this);
        }
    }
}
