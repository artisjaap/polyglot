package be.artisjaap.document.cucumber;

import be.artisjaap.document.DocumentbeheerInMemoryTestParent;
import be.artisjaap.document.model.mongo.CombinedTemplateRepository;
import be.artisjaap.document.model.mongo.DocumentRepository;
import be.artisjaap.document.model.mongo.GegenereerdeBriefRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import cucumber.api.java.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(Cucumber.class)
public class CucumberHooks extends DocumentbeheerInMemoryTestParent {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private CombinedTemplateRepository combinedTemplateRepository;


    @Autowired
    private GegenereerdeBriefRepository gegenereerdeBriefRepository;

    @Before
    public void clear() {
        documentRepository.deleteAll();
        templateRepository.deleteAll();
        combinedTemplateRepository.deleteAll();
        gegenereerdeBriefRepository.deleteAll();
    }

    @Test
    public void empty() {
    }

}
