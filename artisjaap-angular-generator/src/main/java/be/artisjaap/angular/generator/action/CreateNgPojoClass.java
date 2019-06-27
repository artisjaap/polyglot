package be.artisjaap.angular.generator.action;

import be.artisjaap.angular.generator.action.vo.ClassVO;
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
public class CreateNgPojoClass {


    public String forClass(ClassVO classVO) {

        try (Reader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/ng-templates/pojo-class.ftl")))) {
            Template t = new Template("name", reader,
                    new Configuration(new Version("2.3.0")));

            return FreeMarkerTemplateUtils.processTemplateIntoString(t, classVO);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }


        return "";
    }

}
