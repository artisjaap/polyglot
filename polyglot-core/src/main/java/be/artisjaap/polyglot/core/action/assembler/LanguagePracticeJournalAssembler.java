package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.model.LanguagePracticeJournal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguagePracticeJournalAssembler implements Assembler<LanguagePracticeJournalTO, LanguagePracticeJournal> {
    @Autowired
    private TranslationJournalAssembler translationJournalAssembler;

    @Override
    public LanguagePracticeJournalTO assembleTO(LanguagePracticeJournal doc) {
        return LanguagePracticeJournalTO.newBuilder()
                .withLanguagePairId(doc.getLanguagePairId().toString())
                .withUserId(doc.getUserId().toString())
                .withYearMonth(doc.getYearMonth())
                .withTranslationJournalList(translationJournalAssembler.assembleTOs(doc.getTranslationJournalList()))
                .build();
    }

    @Override
    public LanguagePracticeJournal assembleEntity(LanguagePracticeJournalTO languagePracticeJournalTO) {
        throw new UnsupportedOperationException();
    }
}
