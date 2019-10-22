package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.User;
import be.artisjaap.polyglot.core.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserPersister {

    @Autowired
    private UserRepository userRepository;



    public User randomUser(){
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        return userRepository.save(UserMother.initRandom().withRoles(roles).build());
    }
}
