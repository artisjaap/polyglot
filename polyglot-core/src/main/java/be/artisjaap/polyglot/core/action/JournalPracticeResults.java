package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.to.AnswerTO;
import be.artisjaap.polyglot.core.model.LanguagePracticeJournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalPracticeResults {

    @Autowired
    private LanguagePracticeJournalRepository languagePracticeJournalRepository;

    public void forResult(AnswerTO answerTO){
        languagePracticeJournalRepository.addAnswerToList(answerTO);

    }
}
