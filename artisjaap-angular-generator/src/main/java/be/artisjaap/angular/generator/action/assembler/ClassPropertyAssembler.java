package be.artisjaap.angular.generator.action.assembler;

import be.artisjaap.angular.generator.action.vo.ClassPropertyVO;
import be.artisjaap.common.action.Assembler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ClassPropertyAssembler implements Assembler<ClassPropertyVO, Field> {
    @Override
    public ClassPropertyVO assembleTO(Field doc) {
        return ClassPropertyVO.newBuilder()
                .withName(doc.getName())
                .withType(doc.getType().getSimpleName())
                .build();

    }

    @Override
    public Field assembleEntity(ClassPropertyVO classPropertyVO) {
        return null;
    }
}
