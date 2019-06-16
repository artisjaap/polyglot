package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.User;
import be.artisjaap.polyglot.core.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPersister {

    @Autowired
    private UserRepository userRepository;



    public User randomUser(){
        return userRepository.save(UserMother.initRandom().build());
    }
}
