package be.artisjaap.document.action;

import be.artisjaap.document.DocumentbeheerInMemoryTestParent;
import be.artisjaap.document.persisters.TemplatePersister;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public class EenvoudigeTemplateToevoegenInMemoryTest extends DocumentbeheerInMemoryTestParent {

    @Resource
    private TemplatePersister templatePersister;

    @Resource
    private TemplateRepository templateRepository;

    @Test
    public void eenTemplateOpladen() throws IOException{

        templatePersister.maakTemplate("TEST_TEMPLATE", "TEST_TEMPLATE.DOCX");

        List<Template> all = templateRepository.findAll();
        Assert.assertEquals(1, all.size());
    }
}
