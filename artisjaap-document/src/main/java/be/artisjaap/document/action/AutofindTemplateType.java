package be.artisjaap.document.action;

import be.artisjaap.document.model.GecombineerdeTemplate;
import be.artisjaap.document.model.PaginaType;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AutofindTemplateType {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private GecombineerdeTemplateRepository gecombineerdeTemplateRepository;

    public PaginaType voorPaginaCode(String code){
        Optional<Template> template = templateRepository.findByCode(code).stream().findFirst();
        if(template.isPresent()){
            return PaginaType.TEMPLATE;
        }

        List<GecombineerdeTemplate> gecombineerdeTemplate = gecombineerdeTemplateRepository.findByCode(code);
        if(!gecombineerdeTemplate.isEmpty()){
            return PaginaType.GECOMBINEERD;
        }

        return PaginaType.UNDEFINED;


    }
}
