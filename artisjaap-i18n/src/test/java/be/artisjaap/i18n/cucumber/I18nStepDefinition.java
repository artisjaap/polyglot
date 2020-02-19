package be.artisjaap.i18n.cucumber;

import be.artisjaap.i18n.action.I18nCreateTranslation;
import be.artisjaap.i18n.action.I18nFindTranslation;
import be.artisjaap.i18n.action.I18nTranslationBundle;
import be.artisjaap.i18n.action.I18nTranslationCache;
import be.artisjaap.i18n.action.to.NewTranslationTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
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


    @Given("A i18n service")
    public void backupService() {
        System.out.println("i18n Service");
    }


    @Given("A translation with key {translationKey} has value {translation} in {userLanguage}")
    public void aTranslationWithKeyMessageHasValueBoodschapInNL(String key, String value, String language) {
        i18nCreateTranslation.forTranslation(NewTranslationTO.builder()
                .key(key)
                .languageIsoCode(language)
                .translation(value)
                .bundleName("common")
                .build());
    }

    @When("The translation of key {translationKey} in {userLanguage} has value {translation}")
    public void iRequestTranslationOfKeyMessageInNLItHasValueBoodschap(String key, String language, String value) {
        Optional<String> translation = i18nFindTranslation.translationForKey(key, language);
        Assert.assertThat(translation.get(), CoreMatchers.is(value));

    }
}
