package be.artisjaap.angular.generator.action.assembler;

import be.artisjaap.angular.generator.action.ConvertClassTypeToNg;
import be.artisjaap.angular.generator.action.vo.ClassPropertyVO;
import be.artisjaap.common.action.Assembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ClassPropertyAssembler implements Assembler<ClassPropertyVO, Field> {
    @Autowired
    private ClassAssembler classAssembler;

    @Override
    public ClassPropertyVO assembleTO(Field doc) {
        return ClassPropertyVO.newBuilder()
                .withName(doc.getName())
                .withIsListType(ConvertClassTypeToNg.isList(doc.getClass()))
                .withType(ConvertClassTypeToNg.convertToNgTypeForField(doc.getType()))
                .withTypeClass(classAssembler.assembleTO(doc.getType().getClass()))
                .build();

    }

    @Override
    public Field assembleEntity(ClassPropertyVO classPropertyVO) {
        return null;
    }
}
