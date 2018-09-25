package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.NewUserAssembler;
import be.artisjaap.polyglot.core.action.assembler.UserAssembler;
import be.artisjaap.polyglot.core.action.to.NewUserTO;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.core.model.User;
import be.artisjaap.polyglot.core.model.UserRepository;
import be.artisjaap.polyglot.core.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUser {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewUserAssembler newUserAssembler;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private UserValidator userValidator;

    public UserTO newUser(NewUserTO newUserTO){
        User user = newUserAssembler.assembleEntity(newUserTO);
        userValidator.validateInsert(user);
        userRepository.save(user);
        return userAssembler.assembleTO(user);

    }
}
