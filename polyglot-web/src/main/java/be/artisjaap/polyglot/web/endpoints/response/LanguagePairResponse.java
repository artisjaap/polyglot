package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LanguagePairTO;

import java.time.LocalDateTime;

public class LanguagePairResponse extends AbstractReferenceableResponse{

    private String userId;
    private String languageFrom;
    private String languageTo;
    private Integer turnsDone;
    private Integer turnsDoneReverse;
    private LocalDateTime lastTurn;
    private LocalDateTime lastTurnReverse;

    private LanguagePairResponse(Builder builder) {
        buildCommon(builder);
        userId = builder.userId;
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
        turnsDone = builder.turnsDone;
        turnsDoneReverse = builder.turnsDoneReverse;
        lastTurn = builder.lastTurn;
        lastTurnReverse = builder.lastTurnReverse;
    }

    public static LanguagePairResponse from(LanguagePairTO to) {
        return newBuilder()
                .forDocument(to)
                .withLanguageFrom(to.languageFrom())
                .withLanguageTo(to.languageTo())
                .withLastTurn(to.lastTurn())
                .withLastTurnReverse(to.lastTurnReverse())
                .withTurnsDone(to.turnsDone())
                .withTurnsDoneReverse(to.turnsDoneReverse())
                .withUserId(to.userId())
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUserId() {
        return userId;
    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public String getLanguageTo() {
        return languageTo;
    }

    public Integer getTurnsDone() {
        return turnsDone;
    }

    public Integer getTurnsDoneReverse() {
        return turnsDoneReverse;
    }

    public LocalDateTime getLastTurn() {
        return lastTurn;
    }

    public LocalDateTime getLastTurnReverse() {
        return lastTurnReverse;
    }

    public static final class Builder extends AbstractBuilder<Builder>{
        private String userId;
        private String languageFrom;
        private String languageTo;
        private Integer turnsDone;
        private Integer turnsDoneReverse;
        private LocalDateTime lastTurn;
        private LocalDateTime lastTurnReverse;

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withLanguageFrom(String val) {
            languageFrom = val;
            return this;
        }

        public Builder withLanguageTo(String val) {
            languageTo = val;
            return this;
        }

        public Builder withTurnsDone(Integer val) {
            turnsDone = val;
            return this;
        }

        public Builder withTurnsDoneReverse(Integer val) {
            turnsDoneReverse = val;
            return this;
        }

        public Builder withLastTurn(LocalDateTime val) {
            lastTurn = val;
            return this;
        }

        public Builder withLastTurnReverse(LocalDateTime val) {
            lastTurnReverse = val;
            return this;
        }

        public LanguagePairResponse build() {
            return new LanguagePairResponse(this);
        }
    }
}
