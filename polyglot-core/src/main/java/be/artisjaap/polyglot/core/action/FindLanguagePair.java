package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.LanguagePairAssembler;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewLanguagePairTO;
import be.artisjaap.polyglot.core.model.LanguagePairRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindLanguagePair {

    @Autowired
    private LanguagePairRepository languagePairRepository;

    @Autowired
    private LanguagePairAssembler languagePairAssembler;

    @Autowired
    private RegisterLanguagePair registerLanguagePair;

    public List<LanguagePairTO> allPairsForUserId(String userId){
        return languagePairAssembler.assembleTOs(languagePairRepository.findAllByUserId(new ObjectId(userId)));

    }

    public Optional<LanguagePairTO> pairForUser( String userId, String languageFrom, String languageTo){
        return languagePairRepository.findByUserIdAndLanguageFromAndLanguageTo(new ObjectId(userId), languageFrom, languageTo)
                .map(languagePairAssembler::assembleTO);
    }

    public LanguagePairTO pairForUserOrCreate( String userId, String languageFrom, String languageTo){
        Optional<LanguagePairTO> languagePair = pairForUser(userId, languageFrom, languageTo);
        if(languagePair.isPresent()){
            return languagePair.get();
        }

        languagePair = pairForUser(userId, languageTo, languageFrom);
        if(languagePair.isPresent()){
            return languagePair.get();
        }

        return registerLanguagePair.forUser(NewLanguagePairTO.newBuilder()
                .withUserId(userId)
                .withLanguageFrom(languageFrom)
                .withLanguageTo(languageTo)
                .build());
    }


    public Optional<LanguagePairTO> byId(String languagePairId) {
        return languagePairRepository.findById(new ObjectId(languagePairId)).map(languagePairAssembler::assembleTO);
    }
}
