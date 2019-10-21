package be.artisjaap.i18n.cucumber;

import be.artisjaap.i18n.action.I18nCreateTranslation;
import be.artisjaap.i18n.action.I18nFindTranslation;
import be.artisjaap.i18n.action.I18nTranslationBundle;
import be.artisjaap.i18n.action.I18nTranslationCache;
import be.artisjaap.i18n.action.to.NewTranslationTO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class I18nStepDefinition {
    @Autowired
    private I18nCreateTranslation i18nCreateTranslation;

    @Autowired
    private I18nFindTranslation i18nFindTranslation;

    @Autowired
    private I18nTranslationBundle i18nTranslationBundle;

    @Autowired
    private I18nTranslationCache i18nTranslationCache;


    @Given("^A i18n service$")
    public void backupService() {
        System.out.println("i18n Service");
    }


    @Given("^A translation with key (.*) has value (.*) in (.*)$")
    public void aTranslationWithKeyMessageHasValueBoodschapInNL(String key, String value, String language) {
        i18nCreateTranslation.forTranslation(NewTranslationTO.newBuilder().withKey(key)
                .withLanguageIsoCode(language)
                .withTranslation(value)
                .withBundleName("common")
                .build());
    }

    @When("^The translation of key (.*) in (.*) has value (.*)$")
    public void iRequestTranslationOfKeyMessageInNLItHasValueBoodschap(String key, String language, String value) {
        Optional<String> translation = i18nFindTranslation.translationForKey(key, language);
        Assert.assertThat(translation.get(), CoreMatchers.is(value));

    }
}
