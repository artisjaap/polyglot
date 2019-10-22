package be.artisjaap.polyglot.cucumber;

import be.artisjaap.document.DocumentbeheerApplication;
import be.artisjaap.polyglot.PolyglotApplication;
import be.artisjaap.polyglot.core.model.LanguagePairRepository;
import be.artisjaap.polyglot.core.model.LessonRepository;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import be.artisjaap.polyglot.core.model.UserRepository;
import be.artisjaap.properties.PropertiesApplication;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = {PolyglotApplication.class, DocumentbeheerApplication.class, PropertiesApplication.class})
public class CucumberHooks {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TranslationRepository translationRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private LanguagePairRepository languagePairRepository;


    @Before
    public void clear() {
        userRepository.deleteAll();
        translationRepository.deleteAll();
        lessonRepository.deleteAll();
        languagePairRepository.deleteAll();
    }


}
