package be.artisjaap.document.persisters;

import be.artisjaap.document.action.ActivateSimpleOrCombinedTemplate;
import be.artisjaap.document.action.AddSimpleTemplate;
import be.artisjaap.document.action.to.TemplateNewTO;
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
    private AddSimpleTemplate addSimpleTemplate;

    @Resource
    private ActivateSimpleOrCombinedTemplate activateSimpleTemplate;

    public void maakTemplate(String code, String filename) {

        File file = new File(this.getClass().getResource(filename).getFile());

        try {
            TemplateNewTO templateTO = TemplateNewTO.newBuilder()
                    .withCode(code)
                    .withOriginalFilename(filename)
                    .withTaal("DUTCH")
                    .withTemplate(FileUtils.readFileToByteArray(file))

                    .build();
            TemplateTO templateOpgeslagen = addSimpleTemplate.forNew(templateTO);
            activateSimpleTemplate.activateTemplate(templateOpgeslagen.getId());
        }catch(IOException x){
            Assert.fail(x.getMessage());
        }
    }

}
