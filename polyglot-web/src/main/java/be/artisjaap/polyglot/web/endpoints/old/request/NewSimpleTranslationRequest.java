package be.artisjaap.polyglot.web.endpoints.old.request;

import java.util.Set;

public class NewSimpleTranslationRequest {
    private Set<String> question;
    private Set<String> solution;

    public NewSimpleTranslationRequest(){}

    public Set<String> getQuestion() {
        return question;
    }

    public void setQuestion(Set<String> question) {
        this.question = question;
    }

    public Set<String> getSolution() {
        return solution;
    }

    public void setSolution(Set<String> solution) {
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
        private Set<String> question;
        private Set<String> solution;

        private Builder() {
        }

        public Builder question(Set<String> val) {
            question = val;
            return this;
        }

        public Builder solution(Set<String> val) {
            solution = val;
            return this;
        }

        public NewSimpleTranslationRequest build() {
            return new NewSimpleTranslationRequest(this);
        }
    }
}
