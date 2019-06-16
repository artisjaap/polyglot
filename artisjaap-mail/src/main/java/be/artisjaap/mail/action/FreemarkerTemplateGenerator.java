package be.artisjaap.mail.action;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.StringReader;


@Component
public class FreemarkerTemplateGenerator {


    public String fillTemplate(String templateString, Object model) {
        try {
            Template t = new Template("name", new StringReader(templateString),
                    new Configuration(new Version("1")));

            return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        }catch(Exception ex){
            throw new IllegalStateException("Something went wrong");
        }

    }
}
