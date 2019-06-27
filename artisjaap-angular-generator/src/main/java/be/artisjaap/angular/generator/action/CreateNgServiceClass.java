package be.artisjaap.angular.generator.action;

import be.artisjaap.angular.generator.action.vo.ServiceClassVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class CreateNgServiceClass {


    public String forClass(ServiceClassVO serviceClassVO) {
        try (Reader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/ng-templates/service-class.ftl")))) {
            Template t = new Template("name", reader,
                    new Configuration(new Version("2.3.0")));

            String serviceClass = FreeMarkerTemplateUtils.processTemplateIntoString(t, serviceClassVO);

            return serviceClass;
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }


        return "";

    }


}
