package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;
import be.artisjaap.polyglot.core.model.TranslationJournal;
import org.springframework.stereotype.Component;

@Component
public class TranslationJournalAssembler implements Assembler<TranslationJournalTO, TranslationJournal> {
    @Override
    public TranslationJournalTO assembleTO(TranslationJournal doc) {
        return TranslationJournalTO.newBuilder()
                .withCorrect(doc.getCorrect())
                .withExpectedAnswer(doc.getExpectedAnswer())
                .withGivenAnswer(doc.getGivenAnswer())
                .withQuestion(doc.getQuestion())
                .withTimestamp(doc.getTimestamp())
                .withTranslationId(doc.getTranslationId().toString())
                .build();
    }

    @Override
    public TranslationJournal assembleEntity(TranslationJournalTO translationJournalTO) {
        throw new UnsupportedOperationException();
    }
}
