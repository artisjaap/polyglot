package be.artisjaap.i18n.cucumber;

import be.artisjaap.i18n.action.CreateTranslation;
import be.artisjaap.i18n.action.FindTranslation;
import be.artisjaap.i18n.action.TranslationBundle;
import be.artisjaap.i18n.action.TranslationCache;
import be.artisjaap.i18n.action.to.NewTranslationTO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class I18nStepDefinition {
    @Autowired
    private CreateTranslation createTranslation;

    @Autowired
    private FindTranslation findTranslation;

    @Autowired
    private TranslationBundle translationBundle;

    @Autowired
    private TranslationCache translationCache;


    @Given("^A i18n service$")
    public void backupService() {
        System.out.println("i18n Service");
    }


    @Given("^A translation with key (.*) has value (.*) in (.*)$")
    public void aTranslationWithKeyMessageHasValueBoodschapInNL(String key, String value, String language) {
        createTranslation.forTranslation(NewTranslationTO.newBuilder().withKey(key)
                .withLanguageIsoCode(language)
                .withTranslation(value)
                .withBundleName("common")
                .build());
    }

    @When("^The translation of key (.*) in (.*) has value (.*)$")
    public void iRequestTranslationOfKeyMessageInNLItHasValueBoodschap(String key, String language, String value) {
        Optional<String> translation = findTranslation.translationForKey(key, language);
        Assert.assertThat(translation.get(), CoreMatchers.is(value));

    }
}
