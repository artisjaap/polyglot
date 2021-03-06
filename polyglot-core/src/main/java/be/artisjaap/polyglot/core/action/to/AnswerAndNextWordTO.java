package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.polyglot.core.action.to.lessonpractice.LessonPracticeStatusTO;

public class AnswerAndNextWordTO {
    private AnswerTO answer;
    private PracticeWordTO practiceWord;
    private LessonPracticeStatusTO lessonPracticeStatus;

    private AnswerAndNextWordTO(Builder builder) {
        answer = builder.answer;
        practiceWord = builder.practiceWord;
        lessonPracticeStatus = builder.lessonPracticeStatus;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public AnswerTO answer() {
        return answer;
    }

    public PracticeWordTO practiceWord() {
        return practiceWord;
    }
    
    public LessonPracticeStatusTO lessonPracticeStatus() {
        return lessonPracticeStatus;
    }

    public static final class Builder {
        private AnswerTO answer;
        private PracticeWordTO practiceWord;
        private LessonPracticeStatusTO lessonPracticeStatus;

        private Builder() {
        }

        public Builder withAnswer(AnswerTO val) {
            answer = val;
            return this;
        }

        public Builder withPracticeWord(PracticeWordTO val) {
            practiceWord = val;
            return this;
        }

        public Builder withLessonPracticeStatus(LessonPracticeStatusTO val) {
            lessonPracticeStatus = val;
            return this;
        }

        public AnswerAndNextWordTO build() {
            return new AnswerAndNextWordTO(this);
        }
    }
}
