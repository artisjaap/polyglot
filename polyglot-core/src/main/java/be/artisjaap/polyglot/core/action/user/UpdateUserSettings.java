package be.artisjaap.polyglot.core.action.user;

import be.artisjaap.polyglot.core.action.assembler.UserAssembler;
import be.artisjaap.polyglot.core.action.to.UserSettingsUpdateTO;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.core.model.User;
import be.artisjaap.polyglot.core.model.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserSettings {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAssembler userAssembler;

    public UserTO forUser(UserSettingsUpdateTO userSettingsTO){
        User user = userRepository.findById(new ObjectId(userSettingsTO.userId())).orElseThrow(() -> new UnsupportedOperationException("User with ID not found"));
        user.getUserSettings().setFlagAsKnowWhenWinningStreakHitsX(userSettingsTO.flagAsKnowWhenWinningStreakHitsX());
        user.getUserSettings().setInitialNumberOnPracticeWords(userSettingsTO.initialNumberOnPracticeWords());
        user.getUserSettings().setNewWordEveryXexcersises(userSettingsTO.newWordEveryXexcersises());
        userRepository.save(user);
        return userAssembler.assembleTO(user);


    }
}
