package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.action.to.NewLanguagePairTO;
import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.core.model.PracticeHealth;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class NewLanguagePairAssembler implements Assembler<NewLanguagePairTO, LanguagePair> {

    @Override
    public NewLanguagePairTO assembleTO(LanguagePair entity) {
        throw new UnsupportedOperationException("Cannot assemble NewLanguagePairTO");
    }

    @Override
    public LanguagePair assembleEntity(NewLanguagePairTO newLanguagePairTO) {
        return LanguagePair.newBuilder()
                .withUserId(new ObjectId(newLanguagePairTO.userId()))
                .withLanguageFrom(newLanguagePairTO.languageFrom())
                .withLanguageTo(newLanguagePairTO.languageTo())
                .withTurnsDone(0)
                .withTurnsDoneReverse(0)
                .withPracticeHealth(PracticeHealth.newBuilder().build())
                .build();
    }
}
