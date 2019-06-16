package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler implements Assembler<UserTO, User> {

    @Autowired
    private UserSettingsAssembler userSettingsAssembler;

    @Override
    public UserTO assembleTO(User user) {
        return UserTO.newBuilder()
                .forDocument(user)
                .withUsername(user.getUsername())
                .withPassword(user.getPassword())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withRoles(user.getRoles())
                .withPreferedRole(user.getRoles().iterator().next())
                .withUserSettings(userSettingsAssembler.assembleTO(user.getUserSettings()))
                .build();
    }

    @Override
    public User assembleEntity(UserTO userTO) {
        return null;
    }
}
