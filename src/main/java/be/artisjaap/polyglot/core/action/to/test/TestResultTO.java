package be.artisjaap.polyglot.core.action.to.test;

import java.util.List;

public class TestResultTO {
    private String lessonName;
    private int score;
    private List<WordResultTO> wordResults;

    private TestResultTO(Builder builder) {
        lessonName = builder.lessonName;
        score = builder.score;
        wordResults = builder.wordResults;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String lessonName() {
        return lessonName;
    }

    public int score() {
        return score;
    }

    public List<WordResultTO> wordResults() {
        return wordResults;
    }


    public static final class Builder {
        private String lessonName;
        private int score;
        private List<WordResultTO> wordResults;

        private Builder() {
        }

        public Builder withLessonName(String val) {
            lessonName = val;
            return this;
        }

        public Builder withScore(int val) {
            score = val;
            return this;
        }

        public Builder withWordResults(List<WordResultTO> val) {
            wordResults = val;
            return this;
        }

        public TestResultTO build() {
            return new TestResultTO(this);
        }
    }
}
