package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.core.model.LanguagePairRepository;
import be.artisjaap.polyglot.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguagePairPersister {
    @Autowired
    private LanguagePairRepository languagePairRepository;

    @Autowired
    private UserPersister userPersister;

    public LanguagePair randomForUser(User user){
        return languagePairRepository.save(LanguagePairMother.initRandom()
                .withUserId(user.getId())
                .build());
    }

    public LanguagePair randomLanguagePair(){
        return randomForUser(userPersister.randomUser());
    }
}
