package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.action.to.TranslationJournalTO;
import be.artisjaap.polyglot.core.model.LanguagePracticeJournal;
import be.artisjaap.polyglot.core.model.TranslationJournal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LanguagePracticeJournalAssembler implements Assembler<LanguagePracticeJournalTO, LanguagePracticeJournal> {
    @Autowired
    private TranslationJournalAssembler translationJournalAssembler;

    @Override
    public LanguagePracticeJournalTO assembleTO(LanguagePracticeJournal doc) {
        List<TranslationJournalTO> val = translationJournalAssembler.assembleTOs(doc.getTranslationJournalList());
        List<TranslationJournalTO> collect = val.stream().sorted(Comparator.comparing(TranslationJournalTO::timestamp).reversed()).collect(Collectors.toList());
        List<LocalDateTime> sorted = doc.getTranslationJournalList().stream()
                .map(TranslationJournal::getTimestamp)
                .sorted().collect(Collectors.toList());


        return LanguagePracticeJournalTO.newBuilder()
                .withLanguagePairId(doc.getLanguagePairId().toString())
                .withUserId(doc.getUserId().toString())
                .withFrom(sorted.size()>0?sorted.get(0):null)
                .withUntil(sorted.size()>0?sorted.get(sorted.size()-1):null)
                .withTranslationJournalList(collect)
                .build();
    }

    @Override
    public LanguagePracticeJournal assembleEntity(LanguagePracticeJournalTO languagePracticeJournalTO) {
        throw new UnsupportedOperationException();
    }
}
