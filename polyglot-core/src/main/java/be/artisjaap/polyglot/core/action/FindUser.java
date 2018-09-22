package be.artisjaap.polyglot.core.action;

import be.artisjaap.polyglot.core.action.assembler.UserAssembler;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.core.model.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAssembler userAssembler;

    public Optional<UserTO> byUsername(String username){
        return userRepository.findByUsername(username).map(userAssembler::assembleTO);
    }

    public UserTO byUserId(String userId) {
        return userRepository.findById(new ObjectId(userId)).map(userAssembler::assembleTO).orElseThrow(() -> new UnsupportedOperationException("user not found"));
    }
}
