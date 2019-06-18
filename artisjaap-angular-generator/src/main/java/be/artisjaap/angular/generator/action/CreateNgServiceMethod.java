package be.artisjaap.angular.generator.action;

import be.artisjaap.angular.generator.action.assembler.MethodAssembler;
import be.artisjaap.angular.generator.action.vo.ServiceMethodVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.*;
import java.lang.reflect.Method;

@Component
public class CreateNgServiceMethod {

    @Autowired
    private MethodAssembler methodAssembler;



    public String forMethod(Method method) {
        ServiceMethodVO methodVO = methodAssembler.assembleTO(method);
        return forMethod(methodVO);
    }

    public String forMethod(ServiceMethodVO methodVO) {

        try (Reader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/ng-templates/service-method.ftl")))) {
            Template t = new Template("name", reader,
                    new Configuration(new Version("2.3.0")));

            return FreeMarkerTemplateUtils.processTemplateIntoString(t, methodVO);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }


        return "";
    }

}
