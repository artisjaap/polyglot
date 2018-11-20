package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefTO;
import be.artisjaap.document.assembler.BriefAssembler;
import be.artisjaap.document.model.mongo.BriefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ZoekBeschikbareBrieven {
    @Autowired
    private BriefRepository briefRepository;

    @Autowired
    private BriefAssembler briefAssembler;

    public List<BriefTO> metCodeInTaal(String code, String taal){
        return briefAssembler.assembleTO(briefRepository.findByCodeAndTaal(code, taal));
    }

    public Optional<BriefTO> actiefMetCodeInTaal(String code, String taal){
        return briefRepository.findByCodeAndTaalAndActief(code, taal).map(briefAssembler::assembleTO);
    }



    public List<BriefTO> metCode(String code){
        return briefAssembler.assembleTO(briefRepository.findByCode(code));
    }
}
