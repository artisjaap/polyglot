package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.common.utils.LocalDateUtils;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;

import java.util.List;

public class LanguagePracticeJournalResponse {

    private String userId;
    private String languagePairId;
    private String from;
    private String until;
    private List<TranslationJournalResponse> translationJournalList;

    public static LanguagePracticeJournalResponse from(LanguagePracticeJournalTO to){
        return newBuilder()
                .withLanguagePairId(to.languagePairId())
                .withTranslationJournalList(TranslationJournalResponse.from(to.translationJournalList()))
                .withUserId(to.userId())
                .withFrom(LocalDateUtils.formatIsoDate(to.from()))
                .withUntil(LocalDateUtils.formatIsoDate(to.until()))
                .build();
    }

    private LanguagePracticeJournalResponse(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        from = builder.from;
        until = builder.until;
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

    public String getFrom() {
        return from;
    }

    public String getUntil() {
        return until;
    }

    public List<TranslationJournalResponse> getTranslationJournalList() {
        return translationJournalList;
    }

    public static final class Builder {
        private String userId;
        private String languagePairId;
        private String from;
        private String until;
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

        public Builder withFrom(String val) {
            from = val;
            return this;
        }

        public Builder withUntil(String val) {
            until = val;
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
