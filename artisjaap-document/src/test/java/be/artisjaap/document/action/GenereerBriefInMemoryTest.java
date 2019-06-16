package be.artisjaap.document.action;

import be.artisjaap.document.DocumentbeheerInMemoryTestParent;
import be.artisjaap.document.action.datasets.*;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.DatasetProviderImpl;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.document.persisters.BriefPersister;
import org.junit.Test;

import javax.annotation.Resource;

public class GenereerBriefInMemoryTest extends DocumentbeheerInMemoryTestParent {

    @Resource
    private BriefPersister briefPersister;

    @Resource
    private GenereerBrief genereerBrief;


    @Test
    public void genereerEenFinaleBrief(){
        briefPersister.maakEenBrief("EEN_BRIEF");
        BriefConfigTO briefConfig = BriefConfigTO.newBuilder()
                .withBestandsnaam(FileGeneratieFactory.fromDatasets()
                        .withFilenameParts("Lid.naam")
                        .withFilenameParts("Zone.zonesecretarisNaam")
                        .build())
                .withOpslagLocatie(BriefLocatieFactory.voorAbsolutePath("c:/temp/docs/"))
                .withTaal("DUTCH")
                .withCode("EEN_BRIEF")
                .withDatasetProvider(DatasetProviderImpl.create()
                        .add("Lid", new LidDataset())
                        .add("Secretariaat", new SecretariaatDataset())
                        .add("BriefInfo", new BriefInfoDataset())

                        .add("Zone", new ZoneDataset())
                        .add("Mandaat", new MandaatDataset())
                        .add("Overschrijving", new OverschrijvingDataset()))
                .addDatasetToBlacklist("Secretariaat")
                .build();
        genereerBrief.voor(briefConfig);
    }





}
