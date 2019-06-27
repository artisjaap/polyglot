package be.artisjaap.polyglot.core.action.documebts.docconfig;

import be.artisjaap.document.action.GenerateDocument;
import be.artisjaap.document.action.to.BriefConfigTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateDummyDocument {

    @Autowired
    private DocumentForCodeFactory documentForCodeFactory;


    @Autowired
    private GenerateDocument generateDocument;

    public byte[] forDocumentInLanguage(DocumentCode code, String language){
        BriefConfigTO briefConfigTO = documentForCodeFactory.findForCode(code).documentConfigForPreview(language);
        return generateDocument.forDocument(briefConfigTO).orElseThrow(() -> new UnsupportedOperationException("failed to generate document"));
    }
}
