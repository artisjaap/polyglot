package be.artisjaap.polyglot.core.model.datasets;

import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;

import java.util.List;
import java.util.stream.Collectors;

public class TranslationResultDataSet {
    private String question;
    private String answer;
    private String givenAnswer;
    private Boolean correct;

    private TranslationResultDataSet(Builder builder) {
        question = builder.question;
        answer = builder.answer;
        givenAnswer = builder.givenAnswer;
        correct = builder.correct;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(TranslationResultDataSet copy) {
        Builder builder = new Builder();
        builder.question = copy.getQuestion();
        builder.answer = copy.getAnswer();
        builder.givenAnswer = copy.getGivenAnswer();
        builder.correct = copy.getCorrect();
        return builder;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public static TranslationResultDataSet from(TranslationJournalTO to){
        return newBuilder().withAnswer(to.expectedAnswer())
                .withGivenAnswer(to.givenAnswer())
                .withQuestion(to.question())
                .withCorrect(to.correct())
                .build();
    }
    public static List<TranslationResultDataSet> from(List<TranslationJournalTO> answerTOList) {
        return answerTOList.stream().map(TranslationResultDataSet::from).collect(Collectors.toList());
    }

    public static final class Builder {
        private String question;
        private String answer;
        private String givenAnswer;
        private Boolean correct;

        private Builder() {
        }

        public Builder withQuestion(String val) {
            question = val;
            return this;
        }

        public Builder withAnswer(String val) {
            answer = val;
            return this;
        }

        public Builder withGivenAnswer(String val) {
            givenAnswer = val;
            return this;
        }

        public Builder withCorrect(Boolean val) {
            correct = val;
            return this;
        }

        public TranslationResultDataSet build() {
            return new TranslationResultDataSet(this);
        }
    }
}
