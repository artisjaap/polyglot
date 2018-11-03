package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.AnswerJournalTO;
import be.artisjaap.polyglot.core.action.to.JournalResultTO;
import be.artisjaap.polyglot.core.model.LanguagePracticeJournal;
import be.artisjaap.polyglot.core.model.TranslationJournal;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class JournalResultAssembler implements Assembler<JournalResultTO, LanguagePracticeJournal> {
    @Override
    public JournalResultTO assembleTO(LanguagePracticeJournal doc) {
        return JournalResultTO.newBuilder()
                .withLanguagePairId(doc.getLanguagePairId().toString())
                .withUserId(doc.getUserId().toString())
                .withYearMonth(doc.getYearMonth())
                .withAnswerTOList(doc.getTranslationJournalList().stream().map(this::toAnswerTO).collect(Collectors.toList()))
                .build();
    }

    private AnswerJournalTO toAnswerTO(TranslationJournal translationJournal) {
        return AnswerJournalTO.newBuilder()
                .withCorrect(translationJournal.getCorrect())
                .withExpectedAnswer(translationJournal.getExpectedAnswer())
                .withGivenAnswer(translationJournal.getGivenAnswer())
                .withQuestion(translationJournal.getQuestion())
                .withTimestamp(translationJournal.getTimestamp())
                .withTranslationId(translationJournal.getTranslationId().toString())
                .build();

    }

    @Override
    public LanguagePracticeJournal assembleEntity(JournalResultTO journalResultTO) {
        throw new UnsupportedOperationException();
    }
}
