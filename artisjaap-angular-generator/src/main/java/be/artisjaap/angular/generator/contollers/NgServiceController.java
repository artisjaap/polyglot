package be.artisjaap.angular.generator.contollers;

import be.artisjaap.angular.generator.action.CreateNgPojoClass;
import be.artisjaap.angular.generator.action.CreateNgServiceClass;
import be.artisjaap.angular.generator.action.CreateNgServiceMethod;
import be.artisjaap.angular.generator.action.ServiceClassMetaData;
import be.artisjaap.angular.generator.action.vo.ClassVO;
import be.artisjaap.angular.generator.action.vo.ServiceClassVO;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api/ng/services")
public class NgServiceController {

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @Autowired
    private CreateNgServiceClass createNgServiceClass;

    @Autowired
    private ServiceClassMetaData serviceClassMetaData;

    @Autowired
    private CreateNgPojoClass createNgPojoClass;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> allDetectedServices() {

        return ResponseEntity.ok(listableBeanFactory.getBeansWithAnnotation(Controller.class));
    }

    @RequestMapping(value = "/{service}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> allDetectedServices(@PathVariable String service) {
        Object bean = listableBeanFactory.getBean(service);

        ServiceClassVO classData = serviceClassMetaData.build(bean.getClass());
        String serviceClass = createNgServiceClass.forClass(classData);

        return ResponseEntity.ok(serviceClass);
    }

    @RequestMapping(value = "/{service}/class-imports", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> allRequiredClassesForService(@PathVariable String service){
        Object bean = listableBeanFactory.getBean(service);

        ServiceClassVO serviceClassVO = serviceClassMetaData.build(bean.getClass());

        String classes = serviceClassVO.getImports().stream().map(createNgPojoClass::forClass)
                .collect(Collectors.joining("\n"));

        return ResponseEntity.ok(classes);
    }



}
