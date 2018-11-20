package be.artisjaap.document.cucumber;

import be.artisjaap.document.action.GenereerBrief;
import be.artisjaap.document.action.datasets.*;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.DatasetProvider;
import be.artisjaap.document.api.DatasetProviderImpl;
import be.artisjaap.document.api.DocumentImage;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.document.api.filegeneratie.GenericFileName;
import be.artisjaap.document.model.GecombineerdeTemplate;
import be.artisjaap.document.model.GegenereerdeBrief;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.mongo.GecombineerdeTemplateRepository;
import be.artisjaap.document.model.mongo.GegenereerdeBriefRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import be.artisjaap.document.utils.QrUtils;
import cucumber.api.java.nl.Dan;
import cucumber.api.junit.Cucumber;
import org.apache.commons.io.FileUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(Cucumber.class)
public class DocumentbeheerThenStepsDefinition /*extends DocumentbeheerInMemoryTestParent*/ {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private GecombineerdeTemplateRepository gecombineerdeTemplateRepository;

    @Autowired
    private GenereerBrief genereerBrief;

    @Autowired
    private GegenereerdeBriefRepository gegenereerdeBriefRepository;

    @Dan("^is de template met code (.*) beschikbaar$")
    public Template isDeBriefMetCodeBeschikbaar(String code) {
        Optional<Template> template = templateRepository.findByCode(code).stream().findFirst(); //FIXME
        assertTrue(template.isPresent()); //TODO teospitpas
        return template.get();
    }

    @Dan("^is de gecombineerde template met code (.*) beschikbaar$")
    public GecombineerdeTemplate isDeGecombineerdeTemplateMetCodeBeschikbaar(String code){
        List<GecombineerdeTemplate> template = gecombineerdeTemplateRepository.findByCode(code);
        assertFalse(template.isEmpty());
        return template.get(0);
    }

    @Dan("^staat de template met code (.*) actief$")
    public void staatDeTemplateMetCodeActief(String code){
        assertTrue(isDeBriefMetCodeBeschikbaar(code).getActief());
    }

    @Dan("^staat de gecombineerde template met code (.*) actief$")
    public void staatDeGecombineerdeTemplateMetCodeActief(String code){
        assertTrue(isDeGecombineerdeTemplateMetCodeBeschikbaar(code).getActief());
    }


    @Dan("^kan de brief met code (.*) in taal (.*) gegenereerd worden met een default set van datasets$")
    public void kanDeBriefMetCodeMANDAAT_BRIEFGegenereerdWordenMetEenDefaultSetVanDatasets(String code, String taal) {
        BriefConfigTO briefConfigTO = BriefConfigTO.newBuilder()
                .withCode(code)
                .withTaal(taal)
                .withDatasetProvider(defaultDatasets())
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorAbsolutePath("c:/temp/docs/"))
                .build();
        genereerBrief.voor(briefConfigTO);
    }


    @Dan("^kan de brief met code (.*) in taal (.*) gegenereerd worden met een default set van datasets en worden opgeslagen in de MongoDB$")
    public void kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenInDeMongoDB(String code, String isoCode) throws Throwable {
        BriefConfigTO briefConfigTO = BriefConfigTO.newBuilder()
                .withCode(code)
                .withTaal(isoCode)
                .withDatasetProvider(defaultDatasets())
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorDB())
                .build();
        genereerBrief.voor(briefConfigTO);

        List<GegenereerdeBrief> brief = gegenereerdeBriefRepository.findByBriefCodeAndTaal(code, isoCode);
        assertThat(brief.size(), is(1));
        assertNotNull(brief.get(0).getBriefLocatie().getDocument());
    }

    @Dan("^kan de brief met code (.*) in taal (.*) gegenereerd worden met een default set van datasets en worden opgeslagen in de MongoDB met bestandsnaam (.*)$")
    public void kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenInDeMongoDBMetBestandsnaam(String code, String isoCode, List<String> bestandsnaamDeel) throws Throwable {
        GenericFileName.Builder genericFilenameBuilder = FileGeneratieFactory.fromDatasets();
        bestandsnaamDeel.forEach(genericFilenameBuilder::withFilenameParts);
        BriefConfigTO briefConfigTO = BriefConfigTO.newBuilder()
                .withCode(code)
                .withTaal(isoCode)
                .withDatasetProvider(defaultDatasets())
                .withBestandsnaam(genericFilenameBuilder.build())
                .withOpslagLocatie(BriefLocatieFactory.voorDB())
                .build();
        genereerBrief.voor(briefConfigTO);


    }

    @Dan("^kan de brief met code (.*) in taal (.*) gegenereerd worden met een default set van datasets en worden opgeslagen in de MongoDB en volgende datasets worden geblacklist: (.*)")
    public void kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenInDeMongoDBMetBlacklist(String code, String isoCode, List<String> blackList) throws Throwable {
        BriefConfigTO briefConfigTO = BriefConfigTO.newBuilder()
                .withCode(code)
                .withTaal(isoCode)
                .withDatasetProvider(defaultDatasets())
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorDB())
                .withDatasetsToBlacklist(new HashSet(blackList))
                .build();
        genereerBrief.voor(briefConfigTO);

        List<GegenereerdeBrief> brief = gegenereerdeBriefRepository.findByBriefCodeAndTaal(code, isoCode);
        assertThat(brief.size(), is(1));
        assertNotNull(brief.get(0).getBriefLocatie().getDocument());



    }



    @Dan("^kan de brief met code (.*) in taal (.*) gegenereerd worden met een default set van datasets en worden opgeslagen het absolute path (.*)$")
    public void kanDeBriefMetCodeInTaaldGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenHetAbsolutePath(String code, String isoCode, String absolutePath) throws Throwable {
        BriefConfigTO briefConfigTO = BriefConfigTO.newBuilder()
                .withCode(code)
                .withTaal(isoCode)
                .withDatasetProvider(defaultDatasets())
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorAbsolutePath(absolutePath))
                .build();
        genereerBrief.voor(briefConfigTO);
    }

    @Dan("^kan de brief met code (.*) in taal (.*) gegenereerd worden met een default set van datasets, wordt opgeslagen op het absolute path (.*) en bevat volgende images$")
    public void kanDeBriefMetCodeInTaaldGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenHetAbsolutePathMetImages(String code, String isoCode, String absolutePath, List<GherkinDocumentImage> images) throws Throwable {
        BriefConfigTO.Builder builder = BriefConfigTO.newBuilder()
                .withCode(code)
                .withTaal(isoCode)
                .withDatasetProvider(defaultDatasets())
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorAbsolutePath(absolutePath));

        images.forEach(image -> builder.addImage(DocumentImage.newBuilder().withName(image.bookmarkNaam).withImage(bytesFromImage( image.image)).build()));

        BriefConfigTO briefConfigTO = builder.build();
        genereerBrief.voor(briefConfigTO);
    }

    @Dan("^kan de brief met code (.*) in taal (.*) gegenereerd worden met een default set van datasets en worden opgeslagen op het relatieve path (.*)$")
    public void kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenOpHetRelatievePath(String code, String isoCode, String relativePath) throws Throwable {
        BriefConfigTO briefConfigTO = BriefConfigTO.newBuilder()
                .withCode(code)
                .withTaal(isoCode)
                .withDatasetProvider(defaultDatasets())
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorRelatiefPath(relativePath))
                .build();
        genereerBrief.voor(briefConfigTO);
    }

    @Dan("^kan de brief met code (.*) in taal (.*) gegenereerd worden met een default set van datasets, wordt opgeslagen op het absolute path (.*) en bevat in de QR de volgende gegevens (.*)$")
    public void briefMetCodeInTaalMetDefaultDatasetsEnQrCodeData(String code, String isoCode, String absolutePath, String qrData) {

        BriefConfigTO.Builder builder = BriefConfigTO.newBuilder()
                .withCode(code)
                .withTaal(isoCode)
                .withDatasetProvider(defaultDatasets())
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorAbsolutePath(absolutePath))
                .addImage(DocumentImage.newBuilder().withName("qr").withImage(QrUtils.maakQrMetData(qrData)).build())
//                .addImage(DocumentImage.newBuilder().withName("qr").withImage(bytesFromImage( "qr.png")).build())
                ;



        BriefConfigTO briefConfigTO = builder.build();
        genereerBrief.voor(briefConfigTO);

    }





    private DatasetProvider defaultDatasets(){
        return DatasetProviderImpl.create()
                .add("Lid", new LidDataset())
                .add("Secretariaat", new SecretariaatDataset())
                .add("BriefInfo", new BriefInfoDataset())
                .add("Zone", new ZoneDataset())
                .add("Mandaat", new MandaatDataset())
                .add("Overschrijving", new OverschrijvingDataset());
    }


    @Dan("^is het bestand (.*) in taal (.*) beschikbaar onder de naam (.*)$")
    public void isHetBestandBeschikbaarOnderDeNaam(String documentCode, String isoCode, String bestandsnaam) throws Throwable {
        GegenereerdeBrief gegenereerdeBrief = zoekGegenereerdeBrief(documentCode, isoCode);
        assertThat(gegenereerdeBrief.getBriefLocatie().getGegenereerdeBestandsnaam(), is(bestandsnaam));
    }

    private byte[] bytesFromImage(String bestand){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("images/"+bestand).getFile());
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }



    private GegenereerdeBrief zoekGegenereerdeBrief(String documentCode, String isoCode) {
        List<GegenereerdeBrief> byBriefCodeAndTaal = gegenereerdeBriefRepository.findByBriefCodeAndTaal(documentCode, isoCode);
        assertThat(byBriefCodeAndTaal.size(), is(1));
        return byBriefCodeAndTaal.get(0);
    }

    @Dan("^is het bestand (.*) in taal (.*) beschikbaar en de metadata van de volgende datasets is niet opgeslagen: (.*)")
    public void isHetBestandMANDAAT_BRIEFInTaalNldBeschikbaarEnDeMetadataVanDeVolgendeDatasetsIsNietOpgeslagenLid(String code, String isoCode, List<String> blackList) throws Throwable {
        GegenereerdeBrief gegenereerdeBrief = zoekGegenereerdeBrief(code, isoCode);
        Set<String> datasets = gegenereerdeBrief.getDatasets().keySet();
        assertFalse(blackList.stream().anyMatch(s -> datasets.contains(s)));
    }
}
