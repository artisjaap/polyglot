package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.polyglot.core.model.KnowledgeStatus;
import be.artisjaap.polyglot.core.model.ProgressStatus;

import java.time.LocalDateTime;

public class WordStatsTO {
    private ProgressStatus progressStatus;
    private KnowledgeStatus knowledgeStatus;
    private KnowledgeStatus knowledgeStatusReverse;
    private Integer knowledgeCounterSuccess;
    private Integer knowledgeCounterSuccessReverse;
    private Integer knowledgeCounterMiss;
    private Integer knowledgeCounterMissReverse;
    private Integer answerChecked;
    private Integer answerCheckedReverse;
    private LocalDateTime lastSuccess;
    private LocalDateTime lastSuccessReverse;
    private LocalDateTime lastMiss;
    private LocalDateTime lastMissReverse;

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

    public Integer answerChecked() {
        return answerChecked;
    }

    public Integer answerCheckedReverse() {
        return answerCheckedReverse;
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

    private WordStatsTO(Builder builder) {
        progressStatus = builder.progressStatus;
        knowledgeStatus = builder.knowledgeStatus;
        knowledgeStatusReverse = builder.knowledgeStatusReverse;
        knowledgeCounterSuccess = builder.knowledgeCounterSuccess;
        knowledgeCounterSuccessReverse = builder.knowledgeCounterSuccessReverse;
        knowledgeCounterMiss = builder.knowledgeCounterMiss;
        knowledgeCounterMissReverse = builder.knowledgeCounterMissReverse;
        answerChecked = builder.answerChecked;
        answerCheckedReverse = builder.answerCheckedReverse;
        lastSuccess = builder.lastSuccess;
        lastSuccessReverse = builder.lastSuccessReverse;
        lastMiss = builder.lastMiss;
        lastMissReverse = builder.lastMissReverse;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "WordStatsTO{" +
                "progressStatus=" + progressStatus +
                ", knowledgeStatus=" + knowledgeStatus +
                ", knowledgeStatusReverse=" + knowledgeStatusReverse +
                ", knowledgeCounterSuccess=" + knowledgeCounterSuccess +
                ", knowledgeCounterSuccessReverse=" + knowledgeCounterSuccessReverse +
                ", knowledgeCounterMiss=" + knowledgeCounterMiss +
                ", knowledgeCounterMissReverse=" + knowledgeCounterMissReverse +
                ", answerChecked=" + answerChecked +
                ", answerCheckedReverse=" + answerCheckedReverse +
                ", lastSuccess=" + lastSuccess +
                ", lastSuccessReverse=" + lastSuccessReverse +
                ", lastMiss=" + lastMiss +
                ", lastMissReverse=" + lastMissReverse +
                '}';
    }


    public static final class Builder {
        private ProgressStatus progressStatus;
        private KnowledgeStatus knowledgeStatus;
        private KnowledgeStatus knowledgeStatusReverse;
        private Integer knowledgeCounterSuccess;
        private Integer knowledgeCounterSuccessReverse;
        private Integer knowledgeCounterMiss;
        private Integer knowledgeCounterMissReverse;
        private Integer answerChecked;
        private Integer answerCheckedReverse;
        private LocalDateTime lastSuccess;
        private LocalDateTime lastSuccessReverse;
        private LocalDateTime lastMiss;
        private LocalDateTime lastMissReverse;

        private Builder() {
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

        public Builder withAnswerChecked(Integer answerChecked) {
            this.answerChecked = answerChecked;
            return this;
        }

        public Builder withAnswerCheckedReverse(Integer answerCheckedReverse) {
            this.answerCheckedReverse = answerCheckedReverse;
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

        public WordStatsTO build() {
            return new WordStatsTO(this);
        }
    }
}
