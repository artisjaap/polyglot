package be.artisjaap.angular.generator.action;

import be.artisjaap.angular.generator.action.assembler.ClassAssembler;
import be.artisjaap.angular.generator.action.assembler.MethodAssembler;
import be.artisjaap.angular.generator.action.vo.ClassPropertyVO;
import be.artisjaap.angular.generator.action.vo.ClassVO;
import be.artisjaap.angular.generator.action.vo.ServiceClassVO;
import be.artisjaap.angular.generator.action.vo.ServiceMethodVO;
import be.artisjaap.angular.generator.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ServiceClassMetaData {
    @Autowired
    private MethodAssembler methodAssembler;

    public ServiceClassVO build(Class<?> clazz) {
        List<ServiceMethodVO> methods = Arrays.asList(clazz.getMethods())
                .stream()
                .filter(method -> ReflectionUtils.methodHasAnnotation(method, RequestMapping.class))
                .map(methodAssembler::assembleTO)
                .collect(Collectors.toList());



        ServiceClassVO serviceClassVO = ServiceClassVO.newBuilder()
                .withNgServiceMethods(methods)
                .withName(clazz.getName())
                .withImports(allRequiredClasses(methods))
                .build();

        return serviceClassVO;
    }

    private Set<ClassVO> allRequiredClasses(List<ServiceMethodVO> methods){

        HashSet<ClassVO> vos = new HashSet<>();


        for (ServiceMethodVO method : methods) {
            allRequiredClasses(method, vos);
        }

        return vos;
    }

    private void allRequiredClasses(ServiceMethodVO method, Set<ClassVO> foundClasses){
        Set<ClassVO> classes = method.getRequiredClasses();

        for (ClassVO aClass : classes) {
            findAllClasses(aClass, foundClasses);

        }

    }

    private void findAllClasses(ClassVO classVO, Set<ClassVO> classes){
        if(!classes.contains(classVO)){
            classes.add(classVO);

            classVO.getProperties().stream().map(ClassPropertyVO::getTypeClass)
                    .forEach(vo -> findAllClasses(vo, classes));
        };


    }
}
