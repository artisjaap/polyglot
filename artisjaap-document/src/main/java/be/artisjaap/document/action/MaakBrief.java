package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefCodeNieuwTO;
import be.artisjaap.document.action.to.BriefNieuwTO;
import be.artisjaap.document.action.to.BriefTO;
import be.artisjaap.document.assembler.BriefAssembler;
import be.artisjaap.document.assembler.BriefCodeNieuwAssembler;
import be.artisjaap.document.assembler.BriefNieuwAssembler;
import be.artisjaap.document.model.Brief;
import be.artisjaap.document.model.mongo.BriefCodeRepository;
import be.artisjaap.document.model.mongo.BriefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaakBrief {

    @Autowired
    private BriefNieuwAssembler briefNieuwAssembler;

    @Autowired
    private BriefAssembler briefAssembler;

    @Autowired
    private BriefRepository briefRepository;

    @Autowired
    private BriefCodeRepository briefCodeRepository;

    @Autowired
    private BriefCodeNieuwAssembler briefCodeNieuwAssembler;


    public BriefTO voor(BriefNieuwTO briefTO){
        Brief brief = briefNieuwAssembler.assembleEntity(briefTO);
        brief = briefRepository.save(brief);
        return briefAssembler.assembleTO(brief);
    }

    public void metNieuweCode(BriefCodeNieuwTO templateCode){
        briefCodeRepository.save(briefCodeNieuwAssembler.assembleEntity(templateCode));
    }
}
