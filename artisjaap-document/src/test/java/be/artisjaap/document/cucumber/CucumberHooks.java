package be.artisjaap.document.cucumber;

import be.artisjaap.document.DocumentbeheerInMemoryTestParent;
import be.artisjaap.document.model.mongo.BriefRepository;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateRepository;
import be.artisjaap.document.model.mongo.GegenereerdeBriefRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(Cucumber.class)
public class CucumberHooks extends DocumentbeheerInMemoryTestParent {
    @Autowired
    private BriefRepository briefRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private GecombineerdeTemplateRepository gecombineerdeTemplateRepository;


    @Autowired
    private GegenereerdeBriefRepository gegenereerdeBriefRepository;

    @Before
    public void clear() {
        briefRepository.deleteAll();
        templateRepository.deleteAll();
        gecombineerdeTemplateRepository.deleteAll();
        gegenereerdeBriefRepository.deleteAll();
    }


}
