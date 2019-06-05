package be.artisjaap.document.action;

import be.artisjaap.document.action.to.GecombineerdeTemplateCodeTO;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ZoekGecombineerdeTemplateCodes {
    @Autowired
    private GecombineerdeTemplateCodeRepository gecombineerdeTemplateCodeRepository;

    public List<GecombineerdeTemplateCodeTO> alle() {
        return gecombineerdeTemplateCodeRepository.findAll().stream()
                .map(templateCode -> GecombineerdeTemplateCodeTO.newBuilder()
                        .withCode(templateCode.getCode())
                        .withDescription(templateCode.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
