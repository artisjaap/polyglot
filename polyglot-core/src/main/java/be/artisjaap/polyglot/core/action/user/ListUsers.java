package be.artisjaap.polyglot.core.action.user;

import be.artisjaap.polyglot.core.action.assembler.UserAssembler;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.core.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListUsers {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAssembler userAssembler;

    public List<UserTO> allUsers(){
        return userAssembler.assembleTOs(userRepository.findAll());
    }
}
