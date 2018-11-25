package be.artisjaap.polyglot.core.action.documebts;

import be.artisjaap.document.action.GenereerBrief;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.brieflocatie.BriefLocatieFactory;
import be.artisjaap.document.api.filegeneratie.FileGeneratieFactory;
import be.artisjaap.polyglot.core.action.CreateJournalDocument;
import be.artisjaap.polyglot.core.action.FindLanguagePair;
import be.artisjaap.polyglot.core.action.documebts.docconfig.DocumentCode;
import be.artisjaap.polyglot.core.action.documebts.docconfig.DocumentForCodeFactory;
import be.artisjaap.polyglot.core.action.documebts.docconfig.FieldXmlGenerator;
import be.artisjaap.polyglot.core.action.documebts.docconfig.GenerateDummyDocument;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.LanguagePracticeJournalTO;
import be.artisjaap.polyglot.core.model.datasets.LanguagePairDataSet;
import be.artisjaap.polyglot.core.model.datasets.TranslationResultDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

@Component
public class GenerateReportForJournal {
    @Autowired
    private GenereerBrief genereerBrief;



    @Autowired
    private CreateJournalDocument createJournalDocument;

    @Autowired
    private FindLanguagePair findLanguagePair;

    @Autowired
    private DocumentForCodeFactory documentForCodeFactory;

    @Autowired
    private FieldXmlGenerator fieldXmlGenerator;

    @Autowired
    private GenerateDummyDocument generateDummyDocument;

    public Optional<byte[]> withData(LanguagePracticeJournalTO result){
        //TODO upload word template as action
        createJournalDocument.createOfNotExists();
        LanguagePairTO languagePairTO = findLanguagePair.byId(result.languagePairId());

        BriefConfigTO briefConfigTO = documentForCodeFactory.findForCode(DocumentCode.REPORT_FOR_JOURNAL)
                .translationDataSetFrom(result.translationJournalList())
                .voegLanguagePairDatasetToeVoor(languagePairTO)
                .buildConfig()
                .withBestandsnaam(FileGeneratieFactory.simpleFilename())
                .withOpslagLocatie(BriefLocatieFactory.voorDB())
                .withTaal("DUTCH")
                .withCode(DocumentCode.REPORT_FOR_JOURNAL.name())
                .build();

        //TODO seperate action for fields.xml
        //genereerFieldsXml.voorBrief(briefConfigTO);
        byte[] bytes = fieldXmlGenerator.forDocument(DocumentCode.REPORT_FOR_JOURNAL);
            File xmlFieldsFile = new File("c:/temp/docs/test.fields.xml");
        try(FileOutputStream fos = new FileOutputStream(xmlFieldsFile)) {

            fos.write(bytes);
            fos.flush();
        }catch(Exception e){}

        //TODO testcode
        byte[] dutches = generateDummyDocument.forDocumentInLanguage(DocumentCode.REPORT_FOR_JOURNAL, "DUTCH");
        File preview = new File("c:/temp/docs/preview.pdf");
        try(FileOutputStream fos = new FileOutputStream(preview)) {

            fos.write(dutches);
            fos.flush();
        }catch(Exception e){}


        return genereerBrief.voor(briefConfigTO);
    }

}