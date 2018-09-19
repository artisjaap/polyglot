package be.artisjaap.polyglot.core.model;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class TranslationPractice extends AbstractDocument{

    private ObjectId translationId;
    private ObjectId languagePairId;
    private ObjectId userId;
    private ProgressStatus progressStatus;
    private KnowledgeStatus knowledgeStatus;
    private KnowledgeStatus knowledgeStatusReverse;
    private Integer knowledgeCounterSuccess;
    private Integer knowledgeCounterSuccessReverse;
    private Integer knowledgeCounterMiss;
    private Integer knowledgeCounterMissReverse;
    private LocalDateTime lastSuccess;
    private LocalDateTime lastSuccessReverse;
    private LocalDateTime lastMiss;
    private LocalDateTime lastMissReverse;
    private Integer answerChecked;
    private Integer answerCheckedReverse;

    private TranslationPractice(){}

    private TranslationPractice(Builder builder) {
        buildCommon(builder);
        translationId = builder.translationId;
        languagePairId = builder.languagePairId;
        userId = builder.userId;
        progressStatus = builder.progressStatus;
        knowledgeStatus = builder.knowledgeStatus;
        knowledgeStatusReverse = builder.knowledgeStatusReverse;
        knowledgeCounterSuccess = builder.knowledgeCounterSuccess;
        knowledgeCounterSuccessReverse = builder.knowledgeCounterSuccessReverse;
        knowledgeCounterMiss = builder.knowledgeCounterMiss;
        knowledgeCounterMissReverse = builder.knowledgeCounterMissReverse;
        lastSuccess = builder.lastSuccess;
        lastSuccessReverse = builder.lastSuccessReverse;
        lastMiss = builder.lastMiss;
        lastMissReverse = builder.lastMissReverse;
        answerChecked = builder.answerChecked;
        answerCheckedReverse = builder.answerCheckedReverse;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ObjectId getTranslationId() {
        return translationId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public KnowledgeStatus getKnowledgeStatus() {
        return knowledgeStatus;
    }

    public Integer getKnowledgeCounterSuccess() {
        return knowledgeCounterSuccess;
    }

    public Integer getKnowledgeCounterMiss() {
        return knowledgeCounterMiss;
    }

    public LocalDateTime getLastSuccess() {
        return lastSuccess;
    }

    public LocalDateTime getLastMiss() {
        return lastMiss;
    }

    public ObjectId getLanguagePairId() {
        return languagePairId;
    }

    public Integer getAnswerChecked() {
        return answerChecked;
    }

    public KnowledgeStatus getKnowledgeStatusReverse() {
        return knowledgeStatusReverse;
    }

    public Integer getKnowledgeCounterSuccessReverse() {
        return knowledgeCounterSuccessReverse;
    }

    public Integer getKnowledgeCounterMissReverse() {
        return knowledgeCounterMissReverse;
    }

    public LocalDateTime getLastSuccessReverse() {
        return lastSuccessReverse;
    }

    public LocalDateTime getLastMissReverse() {
        return lastMissReverse;
    }

    public Integer getAnswerCheckedReverse() {
        return answerCheckedReverse;
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }

    public void increaseAnswerChecked(){
        answerChecked++;
    }

    public void increaseAnswerCheckedReverse(){
        answerCheckedReverse++;
    }

    public static final class Builder extends AbstractBuilder<Builder> {
        private ObjectId translationId;
        private ObjectId userId;
        private ProgressStatus progressStatus;
        private KnowledgeStatus knowledgeStatus;
        private KnowledgeStatus knowledgeStatusReverse;
        private ObjectId languagePairId;
        private Integer knowledgeCounterSuccess;
        private Integer knowledgeCounterSuccessReverse;
        private Integer knowledgeCounterMiss;
        private Integer knowledgeCounterMissReverse;
        private LocalDateTime lastSuccess;
        private LocalDateTime lastSuccessReverse;
        private LocalDateTime lastMiss;
        private LocalDateTime lastMissReverse;
        private Integer answerChecked;
        private Integer answerCheckedReverse;

        private Builder() {
        }

        public Builder withTranslationId(ObjectId translationId) {
            this.translationId = translationId;
            return this;
        }

        public Builder withUserId(ObjectId userId) {
            this.userId = userId;
            return this;
        }

        public Builder withProgressStatus(ProgressStatus progressStatus) {
            this.progressStatus = progressStatus;
            return this;
        }

        public Builder withKnowledgeStatus(KnowledgeStatus knowledgeStatus) {
            this.knowledgeStatus = knowledgeStatus;
            return this;
        }

        public Builder withKnowledgeStatusReverse(KnowledgeStatus knowledgeStatusReverse) {
            this.knowledgeStatusReverse = knowledgeStatusReverse;
            return this;
        }


        public Builder withKnowledgeCounterSuccess(Integer knowledgeCounterSuccess) {
            this.knowledgeCounterSuccess = knowledgeCounterSuccess;
            return this;
        }

        public Builder withKnowledgeCounterSuccessReverse(Integer knowledgeCounterSuccessReverse) {
            this.knowledgeCounterSuccessReverse = knowledgeCounterSuccessReverse;
            return this;
        }

        public Builder withKnowledgeCounterMiss(Integer knowledgeCounterMiss) {
            this.knowledgeCounterMiss = knowledgeCounterMiss;
            return this;
        }

        public Builder withKnowledgeCounterMissReverse(Integer knowledgeCounterMissReverse) {
            this.knowledgeCounterMissReverse = knowledgeCounterMissReverse;
            return this;
        }

        public Builder withLastSuccess(LocalDateTime lastSuccess) {
            this.lastSuccess = lastSuccess;
            return this;
        }

        public Builder withLastSuccessReverse(LocalDateTime lastSuccessReverse) {
            this.lastSuccessReverse = lastSuccessReverse;
            return this;
        }

        public Builder withLastMiss(LocalDateTime lastMiss) {
            this.lastMiss = lastMiss;
            return this;
        }

        public Builder withLastMissReverse(LocalDateTime lastMissReverse) {
            this.lastMissReverse = lastMissReverse;
            return this;
        }

        public Builder withLanguagePairId(ObjectId languagePairId) {
            this.languagePairId = languagePairId;
            return this;
        }

        public Builder withAnswerChecked(Integer answerChecked) {
            this.answerChecked = answerChecked;
            return this;
        }

        public Builder withAnswerCheckedReverse(Integer answerCheckedReverse) {
            this.answerCheckedReverse = answerCheckedReverse;
            return this;
        }

        public TranslationPractice build() {
            return new TranslationPractice(this);
        }
    }
}
