package be.artisjaap.polyglot.core.action.to;

public class AnswerAndNextWordTO {
    private AnswerTO answer;
    private PracticeWordTO practiceWord;

    private AnswerAndNextWordTO(Builder builder) {
        answer = builder.answer;
        practiceWord = builder.practiceWord;
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

    public static final class Builder {
        private AnswerTO answer;
        private PracticeWordTO practiceWord;

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

        public AnswerAndNextWordTO build() {
            return new AnswerAndNextWordTO(this);
        }
    }
}
