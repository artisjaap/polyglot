package be.artisjaap.document.action;

import be.artisjaap.document.action.to.CombinedTemplateCodeTO;
import be.artisjaap.document.model.mongo.CombinedTemplateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindCombinedTemplates {

    private final CombinedTemplateCodeRepository combinedTemplateCodeRepository;

    public FindCombinedTemplates(CombinedTemplateCodeRepository combinedTemplateCodeRepository) {
        this.combinedTemplateCodeRepository = combinedTemplateCodeRepository;
    }

    public List<CombinedTemplateCodeTO> allCombinedTemplate() {
        return combinedTemplateCodeRepository.findAll().stream()
                .map(templateCode -> CombinedTemplateCodeTO.builder()
                        .code(templateCode.getCode())
                        .description(templateCode.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
