package be.artisjaap.angular.generator.action.assembler;

import be.artisjaap.angular.generator.action.vo.ClassPropertyVO;
import be.artisjaap.angular.generator.action.vo.ClassVO;
import be.artisjaap.common.action.Assembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassAssembler implements Assembler<ClassVO, Class> {
    @Autowired
    private ClassPropertyAssembler classPropertyAssembler;

    @Override
    public ClassVO assembleTO(Class clazz) {
        return ClassVO.newBuilder()
                .withName(clazz.getSimpleName())
                .withProperties(buildAllFields(clazz))
                .withForClass(clazz)
                .build();
    }

    private List<ClassPropertyVO> buildAllFields(Class clazz) {
        if(clazz == Class.class){
            return new ArrayList<>();
        }
        return Arrays.asList(clazz.getDeclaredFields())
                .stream()
                .map(classPropertyAssembler::assembleTO)
                .collect(Collectors.toList());

    }


    @Override
    public Class assembleEntity(ClassVO classVO) {
        return null;
    }
}
