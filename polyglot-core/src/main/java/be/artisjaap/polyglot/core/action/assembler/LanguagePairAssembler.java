package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.model.LanguagePair;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LanguagePairAssembler implements Assembler<LanguagePairTO, LanguagePair> {

    @Resource
    private PracticeHealthAssembler practiceHealthAssembler;

    @Override
    public LanguagePairTO assembleTO(LanguagePair doc) {
        return LanguagePairTO.newBuilder()
                .forDocument(doc)
                .withUserId(doc.getUserId().toString())
                .withLanguageFrom(doc.getLanguageFrom())
                .withLanguageTo(doc.getLanguageTo())
                .withTurnsDone(doc.getTurnsDone())
                .withTurnsDoneReverse(doc.getTurnsDoneReverse())
                .withLastTurn(doc.getLastTurn())
                .withLastTurnReverse(doc.getLastTurnReverse())
                .withPracticeHealth(practiceHealthAssembler.assembleTO(doc.getPracticeHealth()))
                .build();
    }

    @Override
    public LanguagePair assembleEntity(LanguagePairTO languagePairTO) {
        return null;
    }
}
