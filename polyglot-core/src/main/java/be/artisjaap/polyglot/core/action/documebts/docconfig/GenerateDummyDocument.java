package be.artisjaap.polyglot.core.action.documebts.docconfig;

import be.artisjaap.document.action.GenereerBrief;
import be.artisjaap.document.action.to.BriefConfigTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateDummyDocument {

    @Autowired
    private DocumentForCodeFactory documentForCodeFactory;


    @Autowired
    private GenereerBrief genereerBrief;

    public byte[] forDocumentInLanguage(DocumentCode code, String language){
        BriefConfigTO briefConfigTO = documentForCodeFactory.findForCode(code).documentConfigForPreview(language);
        return genereerBrief.voor(briefConfigTO).orElseThrow(() -> new UnsupportedOperationException("failed to generate document"));
    }
}
