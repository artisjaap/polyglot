package be.artisjaap.angular.generator.contollers;

import be.artisjaap.angular.generator.action.CreateNgServiceClass;
import be.artisjaap.angular.generator.action.CreateNgServiceMethod;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/ng/services")
public class NgServiceController {

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @Autowired
    private CreateNgServiceClass createNgServiceClass;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> allDetectedServices() {

        return ResponseEntity.ok(listableBeanFactory.getBeansWithAnnotation(Controller.class));
    }

    @RequestMapping(value = "/{service}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> allDetectedServices(@PathVariable String service) {
        Object bean = listableBeanFactory.getBean(service);

        String serviceClass = createNgServiceClass.forClass(bean.getClass());

        return ResponseEntity.ok(serviceClass);
    }


}
