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
import java.util.Arrays;
import java.util.Collections;

public class GenerateDocumentInMemoryTest extends DocumentbeheerInMemoryTestParent {

    @Resource
    private BriefPersister briefPersister;

    @Resource
    private GenerateDocument generateDocument;


    @Test
    public void genereerEenFinaleBrief(){
        briefPersister.maakEenBrief("EEN_BRIEF");
        BriefConfigTO briefConfig = BriefConfigTO.builder()
                .bestandsnaam(FileGeneratieFactory.fromDatasets()
                        .withFilenameParts("Lid.naam")
                        .withFilenameParts("Zone.zonesecretarisNaam")
                        .build())
                .opslagSettingsTO(BriefLocatieFactory.voorAbsolutePath("c:/temp/docs/"))
                .taal("DUTCH")
                .code("EEN_BRIEF")
                .datasetProvider(DatasetProviderImpl.create()
                        .add("Lid", new LidDataset())
                        .add("Secretariaat", new SecretariaatDataset())
                        .add("BriefInfo", new BriefInfoDataset())

                        .add("Zone", new ZoneDataset())
                        .add("Mandaat", new MandaatDataset())
                        .add("Overschrijving", new OverschrijvingDataset()))
                .datasetsBlacklist(Collections.singleton("Secretariaat"))
                .build();
        generateDocument.forDocument(briefConfig);
    }





}
