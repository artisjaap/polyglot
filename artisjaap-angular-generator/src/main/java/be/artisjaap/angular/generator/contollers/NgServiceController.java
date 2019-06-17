package be.artisjaap.angular.generator.contollers;

import be.artisjaap.angular.generator.action.CreateNgServiceMethod;
import be.artisjaap.angular.generator.action.vo.ServiceMethodVO;
import be.artisjaap.angular.generator.utils.ReflectionUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api/ng/services")
public class NgServiceController {

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @Autowired
    private CreateNgServiceMethod createNgServiceMethod;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> allDetectedServices() {

        return ResponseEntity.ok(listableBeanFactory.getBeansWithAnnotation(Controller.class));
    }

    @RequestMapping(value = "/{service}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> allDetectedServices(@PathVariable String service) {
        Object bean = listableBeanFactory.getBean(service);

        List<Method> methods = Arrays.asList(bean.getClass().getMethods())
                .stream()
                .filter(method -> ReflectionUtils.methodHasAnnotation(method, RequestMapping.class))
                .collect(Collectors.toList());



        return ResponseEntity.ok(createNgServiceMethod.forMethod(new ServiceMethodVO()));
    }



}
