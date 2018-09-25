package be.artisjaap.polyglot.core.validators;

import be.artisjaap.polyglot.core.model.User;
import be.artisjaap.polyglot.core.model.UserRepository;
import be.artisjaap.polyglot.core.validation.ValidationMessage;
import be.artisjaap.polyglot.core.validation.Validator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserValidator extends Validator<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void validateUpdate(User oldValue, User newValue, Set<ValidationMessage> errorMessages) {

    }

    @Override
    protected void validateDelete(User entity, Set<ValidationMessage> errorMessages) {

    }

    @Override
    protected void validateInsert(User entity, Set<ValidationMessage> errorMessages) {
        userRepository.findByUsername(entity.getUsername()).ifPresent(user -> newMessage(errorMessages, DefaultValidations.USERNAME_ALREADY_EXISTS));
    }

    @Override
    protected MongoRepository<User, ObjectId> getEntityRepository() {
        return userRepository;
    }
}
