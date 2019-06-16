package be.artisjaap.polyglot.core.action.to.test;

import be.artisjaap.polyglot.core.action.to.PracticeWordTO;

import java.util.List;

public class TestAssignmentTO {
    private String lessonId;
    private String lessonName;
    private List<PracticeWordTO> words;

    private TestAssignmentTO(Builder builder) {
        lessonName = builder.lessonName;
        words = builder.words;
        lessonId = builder.lessonId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String lessonName() {
        return lessonName;
    }

    public String lessonId() {
        return lessonId;
    }

    public List<PracticeWordTO> words() {
        return words;
    }


    public static final class Builder {
        private String lessonName;
        private String lessonId;
        private List<PracticeWordTO> words;

        private Builder() {
        }

        public Builder withLessonName(String val) {
            lessonName = val;
            return this;
        }

        public Builder withWords(List<PracticeWordTO> val) {
            words = val;
            return this;
        }

        public Builder withLessonId(String val) {
            lessonId = val;
            return this;
        }

        public TestAssignmentTO build() {
            return new TestAssignmentTO(this);
        }
    }
}
