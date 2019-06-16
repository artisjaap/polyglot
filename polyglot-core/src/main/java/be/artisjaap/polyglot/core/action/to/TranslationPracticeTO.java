package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.common.model.ReferenceableTO;
import be.artisjaap.polyglot.core.model.KnowledgeStatus;
import be.artisjaap.polyglot.core.model.ProgressStatus;

import java.time.LocalDateTime;

public class TranslationPracticeTO extends ReferenceableTO {

    private String translationId;
    private String languagePairId;
    private String userId;
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

    private TranslationPracticeTO(Builder builder) {
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

    public String translationId() {
        return translationId;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public String userId() {
        return userId;
    }

    public ProgressStatus progressStatus() {
        return progressStatus;
    }

    public KnowledgeStatus knowledgeStatus() {
        return knowledgeStatus;
    }

    public KnowledgeStatus knowledgeStatusReverse() {
        return knowledgeStatusReverse;
    }

    public Integer knowledgeCounterSuccess() {
        return knowledgeCounterSuccess;
    }

    public Integer knowledgeCounterSuccessReverse() {
        return knowledgeCounterSuccessReverse;
    }

    public Integer knowledgeCounterMiss() {
        return knowledgeCounterMiss;
    }

    public Integer knowledgeCounterMissReverse() {
        return knowledgeCounterMissReverse;
    }

    public LocalDateTime lastSuccess() {
        return lastSuccess;
    }

    public LocalDateTime lastSuccessReverse() {
        return lastSuccessReverse;
    }

    public LocalDateTime lastMiss() {
        return lastMiss;
    }

    public LocalDateTime lastMissReverse() {
        return lastMissReverse;
    }

    public Integer answerChecked() {
        return answerChecked;
    }

    public Integer answerCheckedReverse() {
        return answerCheckedReverse;
    }

    public static final class Builder extends AbstractBuilder<Builder> {
        private String translationId;
        private String languagePairId;
        private String userId;
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

        private Builder() {
        }

        public Builder withTranslationId(String val) {
            translationId = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withProgressStatus(ProgressStatus val) {
            progressStatus = val;
            return this;
        }

        public Builder withKnowledgeStatus(KnowledgeStatus val) {
            knowledgeStatus = val;
            return this;
        }

        public Builder withKnowledgeStatusReverse(KnowledgeStatus val) {
            knowledgeStatusReverse = val;
            return this;
        }

        public Builder withKnowledgeCounterSuccess(Integer val) {
            knowledgeCounterSuccess = val;
            return this;
        }

        public Builder withKnowledgeCounterSuccessReverse(Integer val) {
            knowledgeCounterSuccessReverse = val;
            return this;
        }

        public Builder withKnowledgeCounterMiss(Integer val) {
            knowledgeCounterMiss = val;
            return this;
        }

        public Builder withKnowledgeCounterMissReverse(Integer val) {
            knowledgeCounterMissReverse = val;
            return this;
        }

        public Builder withLastSuccess(LocalDateTime val) {
            lastSuccess = val;
            return this;
        }

        public Builder withLastSuccessReverse(LocalDateTime val) {
            lastSuccessReverse = val;
            return this;
        }

        public Builder withLastMiss(LocalDateTime val) {
            lastMiss = val;
            return this;
        }

        public Builder withLastMissReverse(LocalDateTime val) {
            lastMissReverse = val;
            return this;
        }

        public Builder withAnswerChecked(Integer val) {
            answerChecked = val;
            return this;
        }

        public Builder withAnswerCheckedReverse(Integer val) {
            answerCheckedReverse = val;
            return this;
        }

        public TranslationPracticeTO build() {
            return new TranslationPracticeTO(this);
        }
    }
}

