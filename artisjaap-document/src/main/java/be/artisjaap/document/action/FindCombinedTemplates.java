package be.artisjaap.document.action;

import be.artisjaap.document.action.to.CombinedTemplateCodeTO;
import be.artisjaap.document.model.mongo.CombinedTemplateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindCombinedTemplates {
    @Autowired
    private CombinedTemplateCodeRepository combinedTemplateCodeRepository;

    public List<CombinedTemplateCodeTO> allCombinedTemplate() {
        return combinedTemplateCodeRepository.findAll().stream()
                .map(templateCode -> CombinedTemplateCodeTO.newBuilder()
                        .withCode(templateCode.getCode())
                        .withDescription(templateCode.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
