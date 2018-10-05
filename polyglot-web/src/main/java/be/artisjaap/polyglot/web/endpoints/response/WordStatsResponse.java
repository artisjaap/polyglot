package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.WordStatsTO;
import be.artisjaap.polyglot.core.model.KnowledgeStatus;
import be.artisjaap.polyglot.core.model.ProgressStatus;

import java.time.LocalDateTime;

public class WordStatsResponse {
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

    private WordStatsResponse(Builder builder) {
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


    public static WordStatsResponse from(WordStatsTO wordStatsTO) {
        return WordStatsResponse.newBuilder()
        .withProgressStatus(wordStatsTO.progressStatus())
        .withKnowledgeStatus(wordStatsTO.knowledgeStatus())
        .withKnowledgeStatusReverse(wordStatsTO.knowledgeStatusReverse())
        .withKnowledgeCounterSuccess(wordStatsTO.knowledgeCounterSuccess())
        .withKnowledgeCounterSuccessReverse(wordStatsTO.knowledgeCounterSuccessReverse())
        .withKnowledgeCounterMiss(wordStatsTO.knowledgeCounterMiss())
        .withKnowledgeCounterMissReverse(wordStatsTO.knowledgeCounterMissReverse())
        .withAnswerChecked(wordStatsTO.answerChecked())
        .withAnswerCheckedReverse(wordStatsTO.answerCheckedReverse())
        .withLastSuccess(wordStatsTO.lastSuccess())
        .withLastSuccessReverse(wordStatsTO.lastSuccessReverse())
        .withLastMiss(wordStatsTO.lastMiss())
        .withLastMissReverse(wordStatsTO.lastMissReverse())
                .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public KnowledgeStatus getKnowledgeStatus() {
        return knowledgeStatus;
    }

    public KnowledgeStatus getKnowledgeStatusReverse() {
        return knowledgeStatusReverse;
    }

    public Integer getKnowledgeCounterSuccess() {
        return knowledgeCounterSuccess;
    }

    public Integer getKnowledgeCounterSuccessReverse() {
        return knowledgeCounterSuccessReverse;
    }

    public Integer getKnowledgeCounterMiss() {
        return knowledgeCounterMiss;
    }

    public Integer getKnowledgeCounterMissReverse() {
        return knowledgeCounterMissReverse;
    }

    public Integer getAnswerChecked() {
        return answerChecked;
    }

    public Integer getAnswerCheckedReverse() {
        return answerCheckedReverse;
    }

    public LocalDateTime getLastSuccess() {
        return lastSuccess;
    }

    public LocalDateTime getLastSuccessReverse() {
        return lastSuccessReverse;
    }

    public LocalDateTime getLastMiss() {
        return lastMiss;
    }

    public LocalDateTime getLastMissReverse() {
        return lastMissReverse;
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

        public Builder withAnswerChecked(Integer val) {
            answerChecked = val;
            return this;
        }

        public Builder withAnswerCheckedReverse(Integer val) {
            answerCheckedReverse = val;
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

        public WordStatsResponse build() {
            return new WordStatsResponse(this);
        }
    }
}
