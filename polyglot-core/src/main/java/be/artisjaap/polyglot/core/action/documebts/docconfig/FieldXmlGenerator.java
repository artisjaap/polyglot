package be.artisjaap.polyglot.core.action.documebts.docconfig;

import be.artisjaap.document.action.GenerateFieldsXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FieldXmlGenerator {

    @Autowired
    private DocumentForCodeFactory documentForCodeFactory;

    @Autowired
    private GenerateFieldsXml generateFieldsXml;

    public byte[] forDocument(DocumentCode code){

        return generateFieldsXml.voorDatasets(documentForCodeFactory.findForCode(code)
                .documentConfigForPreview());
    }
}
