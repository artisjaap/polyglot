package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;

import java.util.List;

public class LanguagePracticeJournalResponse {

    private String userId;
    private String languagePairId;
    private String yearMonth;
    private List<TranslationJournalResponse> translationJournalList;

    public static LanguagePracticeJournalResponse from(LanguagePracticeJournalTO to){
        return newBuilder()
                .withLanguagePairId(to.languagePairId())
                .withTranslationJournalList(TranslationJournalResponse.from(to.translationJournalList()))
                .withUserId(to.userId())
                .withYearMonth(to.yearMonth())
                .build();
    }

    private LanguagePracticeJournalResponse(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        yearMonth = builder.yearMonth;
        translationJournalList = builder.translationJournalList;
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

    public List<TranslationJournalResponse> getTranslationJournalList() {
        return translationJournalList;
    }

    public static final class Builder {
        private String userId;
        private String languagePairId;
        private String yearMonth;
        private List<TranslationJournalResponse> translationJournalList;

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

        public Builder withTranslationJournalList(List<TranslationJournalResponse> val) {
            translationJournalList = val;
            return this;
        }

        public LanguagePracticeJournalResponse build() {
            return new LanguagePracticeJournalResponse(this);
        }
    }
}
