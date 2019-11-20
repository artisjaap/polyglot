package be.artisjaap.polyglot.web.endpoints.request;

public class NewSimpleTranslationRequest {
    private String question;
    private String solution;

    public NewSimpleTranslationRequest(){}

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    private NewSimpleTranslationRequest(Builder builder) {
        question = builder.question;
        solution = builder.solution;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String question;
        private String solution;

        private Builder() {
        }

        public Builder question(String val) {
            question = val;
            return this;
        }

        public Builder solution(String val) {
            solution = val;
            return this;
        }

        public NewSimpleTranslationRequest build() {
            return new NewSimpleTranslationRequest(this);
        }
    }
}
