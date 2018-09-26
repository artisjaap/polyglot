package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.core.model.ReferenceableTO;

import java.time.LocalDateTime;

public class LanguagePairTO extends ReferenceableTO {
    private String userId;
    private String languageFrom;
    private String languageTo;
    private Integer turnsDone;
    private Integer turnsDoneReverse;
    private LocalDateTime lastTurn;
    private LocalDateTime lastTurnReverse;


    public String userId() {
        return userId;
    }

    public String languageFrom() {
        return languageFrom;
    }

    public String languageTo() {
        return languageTo;
    }


    public Integer turnsDone() {
        return turnsDone;
    }

    public Integer turnsDoneReverse() {
        return turnsDoneReverse;
    }

    public LocalDateTime lastTurn() {
        return lastTurn;
    }

    public LocalDateTime lastTurnReverse() {
        return lastTurnReverse;
    }

    private LanguagePairTO(Builder builder) {
        buildCommon(builder);
        userId = builder.userId;
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
        turnsDone = builder.turnsDone;
        turnsDoneReverse = builder.turnsDoneReverse;
        lastTurn = builder.lastTurn;
        lastTurnReverse = builder.lastTurnReverse;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder extends AbstractBuilder<Builder> {
        private String userId;
        private String languageFrom;
        private String languageTo;
        private Integer turnsDone;
        private Integer turnsDoneReverse;
        private LocalDateTime lastTurn;
        private LocalDateTime lastTurnReverse;

        private Builder() {
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withLanguageFrom(String languageFrom) {
            this.languageFrom = languageFrom;
            return this;
        }

        public Builder withLanguageTo(String languageTo) {
            this.languageTo = languageTo;
            return this;
        }

        public Builder withTurnsDone(Integer turnsDone) {
            this.turnsDone = turnsDone;
            return this;
        }

        public Builder withTurnsDoneReverse(Integer turnsDoneReverse) {
            this.turnsDoneReverse = turnsDoneReverse;
            return this;
        }

        public Builder withLastTurn(LocalDateTime lastTurn) {
            this.lastTurn = lastTurn;
            return this;
        }

        public Builder withLastTurnReverse(LocalDateTime lastTurnReverse) {
            this.lastTurnReverse = lastTurnReverse;
            return this;
        }

        public LanguagePairTO build() {
            return new LanguagePairTO(this);
        }
    }
}
