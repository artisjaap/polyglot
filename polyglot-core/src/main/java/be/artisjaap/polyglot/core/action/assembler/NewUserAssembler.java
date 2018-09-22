package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.polyglot.core.action.to.NewUserTO;
import be.artisjaap.polyglot.core.model.User;
import org.springframework.stereotype.Component;

@Component
public class NewUserAssembler implements Assembler<NewUserTO, User> {


    @Override
    public NewUserTO assembleTO(User entity) {
        throw new UnsupportedOperationException("Cannot assemble NewUserTO");
    }

    @Override
    public User assembleEntity(NewUserTO newUserTO) {
        return User.newBuilder()
                .withUsername(newUserTO.username())
                .withFirstName(newUserTO.firstName())
                .withLastName(newUserTO.lastName())
                .withPassword(newUserTO.password())
                .build();
    }
}
