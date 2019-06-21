package be.artisjaap.angular.generator.action;

import be.artisjaap.angular.generator.action.vo.ServiceClassVO;
import be.artisjaap.angular.generator.utils.ReflectionUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateNgServiceClass {

    @Autowired
    private CreateNgServiceMethod createNgServiceMethod;

    public String forClass(Class<?> clazz) {

        List<Method> methods = Arrays.asList(clazz.getMethods())
                .stream()
                .filter(method -> ReflectionUtils.methodHasAnnotation(method, RequestMapping.class))
                .collect(Collectors.toList());

        ServiceClassVO serviceClassVO = ServiceClassVO.newBuilder()
                .withNgServiceMethods(methods.stream().map(createNgServiceMethod::forMethod).collect(Collectors.toList()))
                .withName(clazz.getName()).build();


        try (Reader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/ng-templates/service-class.ftl")))) {
            Template t = new Template("name", reader,
                    new Configuration(new Version("2.3.0")));

            return FreeMarkerTemplateUtils.processTemplateIntoString(t, serviceClassVO);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }


        return "";

    }


}
