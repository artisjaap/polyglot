package be.artisjaap.polyglot.cucumber;

import be.artisjaap.polyglot.core.model.LanguagePairRepository;
import be.artisjaap.polyglot.core.model.LessonRepository;
import be.artisjaap.polyglot.core.model.TranslationRepository;
import be.artisjaap.polyglot.core.model.UserRepository;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;


public class CucumberHooks extends CucumberInMemoryTestParent {

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
