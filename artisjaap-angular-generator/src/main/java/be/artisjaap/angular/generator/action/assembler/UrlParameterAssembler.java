package be.artisjaap.angular.generator.action.assembler;

import be.artisjaap.angular.generator.action.vo.UrlParameterVO;
import be.artisjaap.angular.generator.utils.ReflectionUtils;
import be.artisjaap.common.action.Assembler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Parameter;

@Component
public class UrlParameterAssembler implements Assembler<UrlParameterVO, Parameter> {
    @Override
    public UrlParameterVO assembleTO(Parameter param) {
        return UrlParameterVO.newBuilder().withName(extratParameterName(param)).withType(param.getType().getName()).build();
    }

    private String extratParameterName(Parameter param) {

        PathVariable annotation = ReflectionUtils.findAnnotationOnParameter(param, PathVariable.class).orElseThrow(() -> new IllegalStateException("parameter should have PathVariable"));
        if (StringUtils.isNotBlank(annotation.value())) {
            return annotation.value();
        }
        return param.getName();
    }

    @Override
    public Parameter assembleEntity(UrlParameterVO urlParameterVO) {
        throw new UnsupportedOperationException("cant assemble Parameter");
    }
}
