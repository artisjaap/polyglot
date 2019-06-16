package be.artisjaap.polyglot.core.action.documebts.docconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentForCodeFactory {

    @Autowired
    private List<AbstractDocumentConfig> configuredDocuments;

    public AbstractDocumentConfig findForCode(DocumentCode code){
        return configuredDocuments.stream()
                .filter(doc -> doc.documentCode() == code)
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Code not supported: " + code))
                .createInstance();
    }

}
