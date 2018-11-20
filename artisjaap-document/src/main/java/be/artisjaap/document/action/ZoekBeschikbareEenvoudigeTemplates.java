package be.artisjaap.document.action;

import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.document.assembler.TemplateAssembler;
import be.artisjaap.document.model.mongo.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ZoekBeschikbareEenvoudigeTemplates {

    @Autowired
    private TemplateAssembler templateAssembler;

    @Autowired
    private TemplateRepository templateRepository;

    public List<TemplateTO> metCodeInTaal(String code, String taal){
        return templateAssembler.assembleTO(templateRepository.findByCodeAndTaal(code, taal));

    }

    public Optional<TemplateTO> metCodeInTaalActief(String code, String taal){
        return templateRepository.findByCodeAndTaalAndActief(code, taal).map(templateAssembler::assembleTO);

    }

    public List<TemplateTO> metCode(String code){
        return templateAssembler.assembleTO(templateRepository.findByCode(code));

    }
}
