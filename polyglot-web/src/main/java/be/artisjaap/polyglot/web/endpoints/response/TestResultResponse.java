package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.test.TestResultTO;

import java.util.List;

public class TestResultResponse {
    private String lessonName;
    private int score;
    private List<WordResultResponse> wordResults;

    private TestResultResponse(Builder builder) {
        lessonName = builder.lessonName;
        score = builder.score;
        wordResults = builder.wordResults;
    }

    public static TestResultResponse from(TestResultTO correctTest) {
        return TestResultResponse.newBuilder()
                .withLessonName(correctTest.lessonName())
                .withScore(correctTest.score())
                .withWordResults(WordResultResponse.from(correctTest.wordResults()))
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getLessonName() {
        return lessonName;
    }

    public int getScore() {
        return score;
    }

    public List<WordResultResponse> getWordResults() {
        return wordResults;
    }

    public static final class Builder {
        private String lessonName;
        private int score;
        private List<WordResultResponse> wordResults;

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

        public Builder withWordResults(List<WordResultResponse> val) {
            wordResults = val;
            return this;
        }

        public TestResultResponse build() {
            return new TestResultResponse(this);
        }
    }
}
