package be.artisjaap.polyglot.core.model;

import be.artisjaap.core.model.AbstractDocument;
import org.bson.types.ObjectId;

import java.util.List;

public class TestResult extends AbstractDocument {

    private String lessonName;
    private ObjectId lessonId;
    private ObjectId userId;
    private int score;
    private List<WordResult> wordResults;

    private TestResult(){}

    private TestResult(Builder builder) {
        lessonName = builder.lessonName;
        lessonId = builder.lessonId;
        userId = builder.userId;
        score = builder.score;
        wordResults = builder.wordResults;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public String getLessonName() {
        return lessonName;
    }

    public ObjectId getLessonId() {
        return lessonId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public List<WordResult> getWordResults() {
        return wordResults;
    }

    public static final class Builder {
        private String lessonName;
        private ObjectId lessonId;
        private ObjectId userId;
        private int score;
        private List<WordResult> wordResults;

        private Builder() {
        }

        public Builder withLessonName(String val) {
            lessonName = val;
            return this;
        }

        public Builder withLessonId(ObjectId val) {
            lessonId = val;
            return this;
        }

        public Builder withUserId(ObjectId val) {
            userId = val;
            return this;
        }

        public Builder withScore(int val) {
            score = val;
            return this;
        }

        public Builder withWordResults(List<WordResult> val) {
            wordResults = val;
            return this;
        }

        public TestResult build() {
            return new TestResult(this);
        }
    }
}
