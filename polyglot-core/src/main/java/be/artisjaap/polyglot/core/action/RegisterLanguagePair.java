package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.LanguagePairAssembler;
import be.artisjaap.polyglot.core.action.assembler.NewLanguagePairAssembler;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewLanguagePairTO;
import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.core.model.LanguagePairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterLanguagePair {

    @Autowired
    private NewLanguagePairAssembler newLanguagePairAssembler;

    @Autowired
    private LanguagePairRepository languagePairRepository;

    @Autowired
    private LanguagePairAssembler languagePairAssembler;


    public LanguagePairTO forUser(NewLanguagePairTO to){
        LanguagePair languagePair = newLanguagePairAssembler.assembleEntity(to);
        languagePairRepository.save(languagePair);
        return languagePairAssembler.assembleTO(languagePair);

    }
}
