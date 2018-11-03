package be.artisjaap.polyglot.core.action.to;

import java.util.ArrayList;
import java.util.List;

public class JournalResultTO {

    private String userId;
    private String languagePairId;
    private String yearMonth;
    private List<AnswerJournalTO> answerTOList;

    private JournalResultTO(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        yearMonth = builder.yearMonth;
        answerTOList = builder.answerTOList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUserId() {
        return userId;
    }

    public String getLanguagePairId() {
        return languagePairId;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public List<AnswerJournalTO> getAnswerTOList() {
        return answerTOList;
    }

    public static final class Builder {
        private String userId;
        private String languagePairId;
        private String yearMonth;
        private List<AnswerJournalTO> answerTOList = new ArrayList<>();

        private Builder() {
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withLanguagePairId(String val) {
            languagePairId = val;
            return this;
        }

        public Builder withYearMonth(String val) {
            yearMonth = val;
            return this;
        }

        public Builder withAnswerTOList(List<AnswerJournalTO> val) {
            answerTOList = val;
            return this;
        }

        public JournalResultTO build() {
            return new JournalResultTO(this);
        }
    }
}
