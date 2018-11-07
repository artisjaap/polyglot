package be.artisjaap.polyglot.core.action;

import be.artisjaap.core.utils.MongoUtils;
import be.artisjaap.polyglot.core.action.assembler.LanguagePracticeJournalAssembler;
import be.artisjaap.polyglot.core.action.to.AnswerTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.model.LanguagePracticeJournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class JournalPracticeResults {

    @Autowired
    private LanguagePracticeJournalRepository languagePracticeJournalRepository;

    @Autowired
    private LanguagePracticeJournalAssembler languagePracticeJournalAssembler;

    public void forResult(AnswerTO answerTO){
        languagePracticeJournalRepository.addAnswerToList(answerTO);
    }

    public LanguagePracticeJournalTO findJournalFor(String userId, String languagePairId, YearMonth yearMonth){
        return languagePracticeJournalRepository.findByUserIdAndLanguagePairIdAndYearMonth(MongoUtils.toObjectId(userId)
                , MongoUtils.toObjectId(languagePairId)
                , yearMonth.toString()).map(languagePracticeJournalAssembler::assembleTO)
                .orElseGet(() -> LanguagePracticeJournalTO.newBuilder().build());
    }
}
