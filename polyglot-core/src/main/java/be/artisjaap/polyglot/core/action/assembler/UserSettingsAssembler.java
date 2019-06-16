package be.artisjaap.polyglot.core.action.assembler;

import be.artisjaap.common.action.Assembler;
import be.artisjaap.polyglot.core.action.to.UserSettingsTO;
import be.artisjaap.polyglot.core.model.UserSettings;
import org.springframework.stereotype.Component;


@Component
public class UserSettingsAssembler implements Assembler<UserSettingsTO, UserSettings> {
    @Override
    public UserSettingsTO assembleTO(UserSettings doc) {
        return UserSettingsTO.newBuilder()
                .withNewWordEveryXexcersises(doc.getNewWordEveryXexcersises())
                .withInitialNumberOnPracticeWords(doc.getInitialNumberOnPracticeWords())
                .withFlagAsKnowWhenWinningStreakHitsX(doc.getFlagAsKnowWhenWinningStreakHitsX())
                .build();
    }

    @Override
    public UserSettings assembleEntity(UserSettingsTO userSettingsTO) {
        throw new UnsupportedOperationException("not implemented");
    }
}
