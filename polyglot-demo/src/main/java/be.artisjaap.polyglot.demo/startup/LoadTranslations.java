package be.artisjaap.polyglot.demo.startup;

import be.artisjaap.migrate.model.scripts.AbstractInitScript;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewTranslationForUserFromFileTO;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.core.action.translation.CreateTranslation;
import be.artisjaap.polyglot.core.action.user.FindUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStreamReader;

@Component
public class LoadTranslations extends AbstractInitScript {

    @Resource
    private CreateTranslation createTranslation;

    @Resource
    private FindUser findUser;

    @Resource
    private FindLanguagePair findLanguagePair;

    @Override
    public String omschrijving() {
        return "Load lessons";
    }

    @Override
    public String getVersion() {
        return "Demo";
    }

    @Override
    public Integer cardinality() {
        return 1010;
    }

    @Override
    public void execute() {
        String userId = findUser.byUsername("stijn").map(UserTO::id).orElseThrow(() -> new IllegalStateException("User stijn not found"));
        String languagePairId = findLanguagePair.pairForUser(userId, "Nederlands", "Frans").map(LanguagePairTO::id).orElseThrow(() -> new IllegalStateException("Language pair not found"));

        createTranslation.forAllWords(NewTranslationForUserFromFileTO.newBuilder()
                .withUserId(userId)
                .withLanguagePairId(languagePairId)
                .withReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("lessons/QE6b-planet-magasin.csv")))
                .build());

        createTranslation.forAllWords(NewTranslationForUserFromFileTO.newBuilder()
                .withUserId(userId)
                .withLanguagePairId(languagePairId)
                .withReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("lessons/QE6b-planet-7-Carnaval.csv")))
                .build());

    }
}
