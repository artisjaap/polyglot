package be.artisjaap.document.action;

import be.artisjaap.document.DocumentbeheerInMemoryTestParent;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.document.persisters.BriefPersister;
import org.junit.Test;

import javax.annotation.Resource;

public class MaakBriefVoorMandaatNaTelefonsischContact extends DocumentbeheerInMemoryTestParent {

    @Resource
    private BriefPersister briefPersister;

    @Resource
    private GenereerBrief genereerBrief;


    @Test
    public void genereerEenFinaleBrief(){
        briefPersister.maakEenBrief("EEN_BRIEF");
        BriefConfigTO briefConfig = BriefConfigTO.newBuilder()
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorAbsolutePath("c:/temp/"))
                .withTaal("DUTCH")
                .withCode("EEN_BRIEF")
                .build();
        genereerBrief.voor(briefConfig);
    }

}

