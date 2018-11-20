package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefCodeTO;
import be.artisjaap.document.model.mongo.BriefCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ZoekBriefCodes {
    @Autowired
    private BriefCodeRepository briefCodeRepository;

    @Autowired
    private ValideerBrief valideerBrief;



    public List<BriefCodeTO> alle(){
        return briefCodeRepository.findAll().stream()
                .map(brief -> BriefCodeTO.newBuilder()
                        .withCode(brief.getCode())
                        .withOmschrijvingNl(brief.getOmschrijvingNl())
                        .withOmschrijvingFr(brief.getOmschrijvingFr())
                        .withGevalideerdNl(valideerBrief.metCodeInTaal(brief.getCode(), "DUTCH"))
                        .withGevalideerdFr(valideerBrief.metCodeInTaal(brief.getCode(), "FRENCH"))
                        .build())
                .collect(Collectors.toList());
    }






}

