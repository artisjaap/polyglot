package be.artisjaap.polyglot.core.model;

import be.artisjaap.common.model.AbstractDocument;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class LanguagePair extends AbstractDocument {
    private ObjectId userId;
    private String languageFrom;
    private String languageTo;
    private LocalDateTime lastTurn;
    private LocalDateTime lastTurnReverse;
    private Integer turnsDone;
    private Integer turnsDoneReverse;
    private PracticeHealth practiceHealth;


    private LanguagePair(){}

    public PracticeHealth getPracticeHealth() {
        return practiceHealth;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public String getLanguageFrom() {
        return languageFrom;
    }

    public String getLanguageTo() {
        return languageTo;
    }

    public LocalDateTime getLastTurn() {
        return lastTurn;
    }

    public Integer getTurnsDone() {
        return turnsDone;
    }

    public LocalDateTime getLastTurnReverse() {
        return lastTurnReverse;
    }

    public Integer getTurnsDoneReverse() {
        return turnsDoneReverse;
    }

    private LanguagePair(Builder builder) {
        userId = builder.userId;
        languageFrom = builder.languageFrom;
        languageTo = builder.languageTo;
        lastTurn = builder.lastTurn;
        lastTurnReverse = builder.lastTurnReverse;
        turnsDone = builder.turnsDone;
        turnsDoneReverse = builder.turnsDoneReverse;
        practiceHealth = builder.practiceHealth;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void increaseTurnReverse() {
        turnsDoneReverse++;
    }

    public void increaseTurn() {
        turnsDone++;
    }


    public static final class Builder {
        private ObjectId userId;
        private String languageFrom;
        private String languageTo;
        private LocalDateTime lastTurn;
        private LocalDateTime lastTurnReverse;
        private Integer turnsDone;
        private Integer turnsDoneReverse;
        private PracticeHealth practiceHealth;

        private Builder() {
        }

        public Builder withUserId(ObjectId userId) {
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

        public Builder withLastTurn(LocalDateTime lastTurn) {
            this.lastTurn = lastTurn;
            return this;
        }

        public Builder withLastTurnReverse(LocalDateTime lastTurnReverse) {
            this.lastTurnReverse = lastTurnReverse;
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

        public Builder withPracticeHealth(PracticeHealth practiceHealth) {
            this.practiceHealth = practiceHealth;
            return this;
        }

        public LanguagePair build() {
            return new LanguagePair(this);
        }
    }
}
