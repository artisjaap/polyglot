package be.artisjaap.polyglot.core.action.to;

import be.artisjaap.core.model.ReferenceableTO;

import java.util.List;

public class LanguagePracticeJournalTO extends ReferenceableTO {

    private String userId;
    private String languagePairId;
    private String yearMonth;
    private List<TranslationJournalTO> translationJournalList;

    private LanguagePracticeJournalTO(Builder builder) {
        buildCommon(builder);
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        yearMonth = builder.yearMonth;
        translationJournalList = builder.translationJournalList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String userId() {
        return userId;
    }

    public String languagePairId() {
        return languagePairId;
    }

    public String yearMonth() {
        return yearMonth;
    }

    public List<TranslationJournalTO> translationJournalList() {
        return translationJournalList;
    }

    public static final class Builder extends AbstractBuilder<Builder> {
        private String userId;
        private String languagePairId;
        private String yearMonth;
        private List<TranslationJournalTO> translationJournalList;

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

        public Builder withTranslationJournalList(List<TranslationJournalTO> val) {
            translationJournalList = val;
            return this;
        }

        public LanguagePracticeJournalTO build() {
            return new LanguagePracticeJournalTO(this);
        }
    }
}
