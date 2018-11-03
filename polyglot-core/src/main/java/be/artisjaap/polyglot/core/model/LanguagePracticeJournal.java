package be.artisjaap.polyglot.core.model;

import be.artisjaap.core.model.AbstractDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;

import java.util.ArrayList;
import java.util.List;

@CompoundIndexes({
        @CompoundIndex(name = "user_lang_month", def = "{'userId' : 1, 'languagePairId': 1, 'yearMonth': 1}")
})
public class LanguagePracticeJournal extends AbstractDocument {

    private ObjectId userId;
    private ObjectId languagePairId;
    private String yearMonth;
    private List<TranslationJournal> translationJournalList;

    private LanguagePracticeJournal(){}

    private LanguagePracticeJournal(Builder builder) {
        userId = builder.userId;
        languagePairId = builder.languagePairId;
        yearMonth = builder.yearMonth;
        translationJournalList = builder.translationJournalList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ObjectId getUserId() {
        return userId;
    }

    public ObjectId getLanguagePairId() {
        return languagePairId;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public List<TranslationJournal> getTranslationJournalList() {
        return translationJournalList;
    }

    public static final class Builder {
        private ObjectId userId;
        private ObjectId languagePairId;
        private String yearMonth;
        private List<TranslationJournal> translationJournalList = new ArrayList<>();

        private Builder() {
        }

        public Builder withUserId(ObjectId val) {
            userId = val;
            return this;
        }

        public Builder withLanguagePairId(ObjectId val) {
            languagePairId = val;
            return this;
        }

        public Builder withYearMonth(String val) {
            yearMonth = val;
            return this;
        }

        public Builder withTranslationJournalList(List<TranslationJournal> val) {
            translationJournalList = val;
            return this;
        }

        public Builder addTranslationJournalList(TranslationJournal val) {
            translationJournalList.add(val);
            return this;
        }

        public LanguagePracticeJournal build() {
            return new LanguagePracticeJournal(this);
        }
    }
}
