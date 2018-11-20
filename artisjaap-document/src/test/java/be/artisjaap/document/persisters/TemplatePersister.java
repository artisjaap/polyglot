package be.artisjaap.document.persisters;

import be.artisjaap.document.action.ActiveerTemplate;
import be.artisjaap.document.action.EenvoudigeTemplateToevoegen;
import be.artisjaap.document.action.to.TemplateNieuwTO;
import be.artisjaap.document.action.to.TemplateTO;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Component
public class TemplatePersister {

    @Resource
    private EenvoudigeTemplateToevoegen eenvoudigeTemplateToevoegen;

    @Resource
    private ActiveerTemplate activeerTemplate;

    public void maakTemplate(String code, String filename) {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("templates/"+filename).getFile());

        try {
            TemplateNieuwTO templateTO = TemplateNieuwTO.newBuilder()
                    .withCode(code)
                    .withOriginalFilename(filename)
                    .withTaal("DUTCH")
                    .withTemplate(FileUtils.readFileToByteArray(file))

                    .build();
            TemplateTO templateOpgeslagen = eenvoudigeTemplateToevoegen.voor(templateTO);
            activeerTemplate.activeerTemplate(templateOpgeslagen.getId());
        }catch(IOException x){
            Assert.fail(x.getMessage());
        }
    }

}
