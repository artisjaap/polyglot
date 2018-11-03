package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.JournalResultAssembler;
import be.artisjaap.polyglot.core.action.to.JournalResultTO;
import be.artisjaap.polyglot.core.model.LanguagePracticeJournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class FindResultsFromJournal {

    @Autowired
    private LanguagePracticeJournalRepository languagePracticeJournalRepository;

    @Autowired
    private JournalResultAssembler journalResultAssembler;

    public JournalResultTO forUser(String userId, String languagePairId, YearMonth yearMonth) {
        return languagePracticeJournalRepository.findByUserIdAndLanguagePairIdAndYearMonth(new ObjectId(userId), new ObjectId(languagePairId), yearMonth.toString())
            .map(journalResultAssembler::assembleTO)
            .orElseGet(() -> JournalResultTO.newBuilder().withUserId(userId).withLanguagePairId(languagePairId).withYearMonth(yearMonth.toString()).build());
    }
}
