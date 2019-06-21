package be.artisjaap.angular.generator.action;

import be.artisjaap.angular.generator.action.assembler.MethodAssembler;
import be.artisjaap.angular.generator.action.vo.ServiceClassVO;
import be.artisjaap.angular.generator.action.vo.ServiceMethodVO;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateNgServiceClass {

    @Autowired
    private CreateNgServiceMethod createNgServiceMethod;

    @Autowired
    private MethodAssembler methodAssembler;

    @Autowired
    private CreateNgPojoClass createNgPojoClass;

    public String forClass(Class<?> clazz) {

        List<ServiceMethodVO> methods = Arrays.asList(clazz.getMethods())
                .stream()
                .filter(method -> ReflectionUtils.methodHasAnnotation(method, RequestMapping.class))
                .map(methodAssembler::assembleTO)
                .collect(Collectors.toList());


        ServiceClassVO serviceClassVO = ServiceClassVO.newBuilder()
                .withNgServiceMethods(methods.stream().map(createNgServiceMethod::forMethod).collect(Collectors.toList()))
                .withName(clazz.getName()).build();

        List<String> pojoClasses = methods.stream()
                .flatMap(m -> m.getRequiredClasses().stream())
                .map(createNgPojoClass::forClass)
                .collect(Collectors.toList());

        try (Reader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/ng-templates/service-class.ftl")))) {
            Template t = new Template("name", reader,
                    new Configuration(new Version("2.3.0")));

            String serviceClass = FreeMarkerTemplateUtils.processTemplateIntoString(t, serviceClassVO);
            String collect = pojoClasses.stream().collect(Collectors.joining("\n"));

            return serviceClass + collect;
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }


        return "";

    }


}
