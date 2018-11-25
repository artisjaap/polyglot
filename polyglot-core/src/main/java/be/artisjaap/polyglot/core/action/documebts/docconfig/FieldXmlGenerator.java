package be.artisjaap.polyglot.core.action.documebts.docconfig;

import be.artisjaap.document.action.GenereerFieldsXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldXmlGenerator {

    @Autowired
    private DocumentForCodeFactory documentForCodeFactory;

    @Autowired
    private GenereerFieldsXml genereerFieldsXml;

    public byte[] forDocument(DocumentCode code){

        return genereerFieldsXml.voorDatasets(documentForCodeFactory.findForCode(code)
                .documentConfigForPreview());
    }
}
