package be.artisjaap.document.action;

import be.artisjaap.document.action.to.TemplateCodeTO;
import be.artisjaap.document.model.mongo.TemplateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindSimpleTemplateCodes {

    private final TemplateCodeRepository templateCodeRepository;

    public FindSimpleTemplateCodes(TemplateCodeRepository templateCodeRepository) {
        this.templateCodeRepository = templateCodeRepository;
    }

    public List<TemplateCodeTO> allSimpleTemplateCodes() {
        return templateCodeRepository.findAll().stream()
                .map(templateCode -> TemplateCodeTO.builder()
                        .code(templateCode.getCode())
                        .description(templateCode.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
