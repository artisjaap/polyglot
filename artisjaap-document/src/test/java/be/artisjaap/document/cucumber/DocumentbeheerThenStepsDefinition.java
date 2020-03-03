package be.artisjaap.document.cucumber;

import be.artisjaap.document.action.GenerateDocument;
import be.artisjaap.document.action.datasets.BriefInfoDataset;
import be.artisjaap.document.action.datasets.LidDataset;
import be.artisjaap.document.action.datasets.MandaatDataset;
import be.artisjaap.document.action.datasets.OverschrijvingDataset;
import be.artisjaap.document.action.datasets.SecretariaatDataset;
import be.artisjaap.document.action.datasets.ZoneDataset;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.DatasetProvider;
import be.artisjaap.document.api.DatasetProviderImpl;
import be.artisjaap.document.api.DocumentImage;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.document.api.filegeneratie.GenericFileName;
import be.artisjaap.document.model.CombinedTemplate;
import be.artisjaap.document.model.GegenereerdeBrief;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.mongo.CombinedTemplateRepository;
import be.artisjaap.document.model.mongo.GegenereerdeBriefRepository;
import be.artisjaap.document.model.mongo.TemplateRepository;
import be.artisjaap.document.utils.QrUtils;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DocumentbeheerThenStepsDefinition {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private CombinedTemplateRepository combinedTemplateRepository;

    @Autowired
    private GenerateDocument generateDocument;

    @Autowired
    private GegenereerdeBriefRepository gegenereerdeBriefRepository;


    @Then("the template with code {code} is available")
    public Template isDeBriefMetCodeBeschikbaar(String code) {
        Optional<Template> template = templateRepository.findByCode(code).stream().findFirst();
        assertTrue(template.isPresent());
        return template.get();
    }

    @Then("the combined template with code {code} is available")
    public CombinedTemplate isDeGecombineerdeTemplateMetCodeBeschikbaar(String code) {
        List<CombinedTemplate> template = combinedTemplateRepository.findByCode(code);
        assertFalse(template.isEmpty());
        return template.get(0);
    }

    @Then("template with code {code} is active")
    public void staatDeTemplateMetCodeActief(String code) {
        assertTrue(isDeBriefMetCodeBeschikbaar(code).getActief());
    }

    @Then("the combined template with code {code} is active")
    public void staatDeGecombineerdeTemplateMetCodeActief(String code) {
        assertTrue(isDeGecombineerdeTemplateMetCodeBeschikbaar(code).getActief());
    }


    @Then("the document with code {code} in language {language} can be generated using a default dataset")
    public void kanDeBriefMetCodeMANDAAT_BRIEFGegenereerdWordenMetEenDefaultSetVanDatasets(String code, String taal) {
        BriefConfigTO briefConfigTO = BriefConfigTO.builder()
                .code(code)
                .taal(taal)
                .datasetProvider(defaultDatasets())
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .opslagSettingsTO(BriefLocatieFactory.voorAbsolutePath("c:/temp/docs/"))
                .build();
        generateDocument.forDocument(briefConfigTO);
    }


    @Then("the document with code {code} in language {language} can be generated using a default dataset and are saved in MongoDB")
    public void kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenInDeMongoDB(String code, String isoCode) throws Throwable {
        BriefConfigTO briefConfigTO = BriefConfigTO.builder()
                .code(code)
                .taal(isoCode)
                .datasetProvider(defaultDatasets())
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .opslagSettingsTO(BriefLocatieFactory.voorDB())
                .build();
        generateDocument.forDocument(briefConfigTO);

        List<GegenereerdeBrief> brief = gegenereerdeBriefRepository.findByBriefCodeAndTaal(code, isoCode);
        assertThat(brief.size(), is(1));
        assertNotNull(brief.get(0).getDocumentLocation().getDocument());
    }

    @Then("the document with code {code} in language {language} can be generated using a default dataset and are saved in MongoDB with filename {filenameParts}")
    public void kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenInDeMongoDBMetBestandsnaam(String code, String isoCode, List<String> bestandsnaamDeel) throws Throwable {
        GenericFileName.Builder genericFilenameBuilder = FileGeneratieFactory.fromDatasets();
        bestandsnaamDeel.forEach(genericFilenameBuilder::withFilenameParts);
        BriefConfigTO briefConfigTO = BriefConfigTO.builder()
                .code(code)
                .taal(isoCode)
                .datasetProvider(defaultDatasets())
                .bestandsnaam(genericFilenameBuilder.build())
                .opslagSettingsTO(BriefLocatieFactory.voorDB())
                .build();
        generateDocument.forDocument(briefConfigTO);


    }

    @Then("the document with code {code} in language {language} can be generated using a default dataset and are saved in MongoDB and datasets {filenameParts} are blacklisted")
    public void kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenInDeMongoDBMetBlacklist(String code, String isoCode, List<String> blackList) throws Throwable {
        BriefConfigTO briefConfigTO = BriefConfigTO.builder()
                .code(code)
                .taal(isoCode)
                .datasetProvider(defaultDatasets())
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .opslagSettingsTO(BriefLocatieFactory.voorDB())
                .datasetsBlacklist(new HashSet(blackList))
                .build();
        generateDocument.forDocument(briefConfigTO);

        List<GegenereerdeBrief> brief = gegenereerdeBriefRepository.findByBriefCodeAndTaal(code, isoCode);
        assertThat(brief.size(), is(1));
        assertNotNull(brief.get(0).getDocumentLocation().getDocument());


    }


    @Then("the document with code {code} in language {language} can be generated using a default dataset and are saved on absolute path {path}")
    public void kanDeBriefMetCodeInTaaldGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenHetAbsolutePath(String code, String isoCode, String absolutePath) throws Throwable {
        BriefConfigTO briefConfigTO = BriefConfigTO.builder()
                .code(code)
                .taal(isoCode)
                .datasetProvider(defaultDatasets())
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .opslagSettingsTO(BriefLocatieFactory.voorAbsolutePath(absolutePath))
                .build();
        generateDocument.forDocument(briefConfigTO);
    }

    @Then("the document with code {code} in language {language} can be generated using a default dataset, are saved in absolute path {path} and contain the following images")
    public void kanDeBriefMetCodeInTaaldGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenHetAbsolutePathMetImages(String code, String isoCode, String absolutePath, List<GherkinDocumentImage> images) throws Throwable {
        List<DocumentImage> documentImages = images.stream()
                .map(image -> DocumentImage.newBuilder().withName(image.bookmarkNaam).withImage(bytesFromImage(image.image)).build())
                .collect(Collectors.toList());

        BriefConfigTO briefConfigTO = BriefConfigTO.builder()
                .code(code)
                .taal(isoCode)
                .datasetProvider(defaultDatasets())
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .opslagSettingsTO(BriefLocatieFactory.voorAbsolutePath(absolutePath))
                .documentImages(documentImages)
                .build();

        generateDocument.forDocument(briefConfigTO);
    }

    @Then("the document with code {code} in language {language} can be generated using a default dataset and are saved on relative path {path}")
    public void kanDeBriefMetCodeInTaalGegenereerdWordenMetEenDefaultSetVanDatasetsEnWordenOpgeslagenOpHetRelatievePath(String code, String isoCode, String relativePath) throws Throwable {
        BriefConfigTO briefConfigTO = BriefConfigTO.builder()
                .code(code)
                .taal(isoCode)
                .datasetProvider(defaultDatasets())
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .opslagSettingsTO(BriefLocatieFactory.voorRelatiefPath(relativePath))
                .build();
        generateDocument.forDocument(briefConfigTO);
    }

    @Then("the document with code {code} in language {language} can be generated using a default dataset, is saved on absolute path {path} and has a QR code with following data {qrData}")
    public void briefMetCodeInTaalMetDefaultDatasetsEnQrCodeData(String code, String isoCode, String absolutePath, String qrData) {

        BriefConfigTO.BriefConfigTOBuilder builder = BriefConfigTO.builder()
                .code(code)
                .taal(isoCode)
                .datasetProvider(defaultDatasets())
                .bestandsnaam(FileGeneratieFactory.simpleFilename())
                .opslagSettingsTO(BriefLocatieFactory.voorAbsolutePath(absolutePath))
                .documentImages(Arrays.asList(DocumentImage.newBuilder().withName("qr").withImage(QrUtils.maakQrMetData(qrData)).build()));


        BriefConfigTO briefConfigTO = builder.build();
        generateDocument.forDocument(briefConfigTO);

    }


    private DatasetProvider defaultDatasets() {
        return DatasetProviderImpl.create()
                .add("Lid", new LidDataset())
                .add("Secretariaat", new SecretariaatDataset())
                .add("BriefInfo", new BriefInfoDataset())
                .add("Zone", new ZoneDataset())
                .add("Mandaat", new MandaatDataset())
                .add("Overschrijving", new OverschrijvingDataset());
    }


    @Then("the document with code {code} in language {language} available with name {file}")
    public void isHetBestandBeschikbaarOnderDeNaam(String documentCode, String isoCode, String bestandsnaam) throws Throwable {
        GegenereerdeBrief gegenereerdeBrief = zoekGegenereerdeBrief(documentCode, isoCode);
        assertThat(gegenereerdeBrief.getDocumentLocation().getGegenereerdeBestandsnaam(), is(bestandsnaam));
    }

    private byte[] bytesFromImage(String bestand) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("images/" + bestand).getFile());
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

    @Then("the document with code {code} in language {language} and ignored datasets are: {filenameParts}")
    public void isHetBestandMANDAAT_BRIEFInTaalNldBeschikbaarEnDeMetadataVanDeVolgendeDatasetsIsNietOpgeslagenLid(String code, String isoCode, List<String> blackList) throws Throwable {
        GegenereerdeBrief gegenereerdeBrief = zoekGegenereerdeBrief(code, isoCode);
        Set<String> datasets = gegenereerdeBrief.getDatasets().keySet();
        assertFalse(blackList.stream().anyMatch(s -> datasets.contains(s)));
    }
}
