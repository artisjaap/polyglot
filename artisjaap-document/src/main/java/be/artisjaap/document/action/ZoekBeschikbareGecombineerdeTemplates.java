package be.artisjaap.document.action;

import be.artisjaap.document.action.to.GecombineerdeTemplateTO;
import be.artisjaap.document.assembler.GecombineerdeTemplateAssembler;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ZoekBeschikbareGecombineerdeTemplates {

    @Autowired
    private GecombineerdeTemplateAssembler gecombineerdeTemplateAssembler;

    @Autowired
    private GecombineerdeTemplateRepository gecombineerdeTemplateRepository;

    public List<GecombineerdeTemplateTO> metCodeInTaal(String code, String taal){
        return gecombineerdeTemplateAssembler.assembleTO(gecombineerdeTemplateRepository.findByCodeAndTaal(code, taal));

    }

    public Optional<GecombineerdeTemplateTO> metCodeInTaalActief(String code, String taal){
        return gecombineerdeTemplateRepository.findByCodeAndTaalAndActief(code, taal).map(gecombineerdeTemplateAssembler::assembleTO);
    }

    public List<GecombineerdeTemplateTO> metCode(String code){
        return gecombineerdeTemplateAssembler.assembleTO(gecombineerdeTemplateRepository.findByCode(code));
    }
}
