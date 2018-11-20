package be.artisjaap.document.action;

import be.artisjaap.document.action.to.GegenereerdeBriefInfoTO;
import be.artisjaap.document.assembler.GegenereerdeBriefInfoAssembler;
import be.artisjaap.document.model.mongo.GegenereerdeBriefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZoekGegenereerdeBrieven {

    @Autowired
    private GegenereerdeBriefRepository gegenereerdeBriefRepository;

    @Autowired
    private GegenereerdeBriefInfoAssembler gegenereerdeBriefInfoAssembler;

    public List<GegenereerdeBriefInfoTO> vanBriefMetCodeInTaal(String code, String taal){
        return gegenereerdeBriefInfoAssembler.assembleTO(gegenereerdeBriefRepository.findByBriefCodeAndTaal(code, taal));
    }
}
