package be.artisjaap.document.action;

import be.artisjaap.document.model.Document;
import be.artisjaap.document.model.mongo.DocumentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActivateDocument {

    private final DocumentRepository documentRepository;
    private final DeactivateDocument deactivateDocument;

    public ActivateDocument(DocumentRepository documentRepository, DeactivateDocument deactivateDocument) {
        this.documentRepository = documentRepository;
        this.deactivateDocument = deactivateDocument;
    }

    public void metId(String id) {
        Optional<Document> briefOptional = documentRepository.findById(new ObjectId(id));
        briefOptional.ifPresent(
                brief -> {
                    deactivateDocument.withCodeAndLanguage(brief.getCode(), brief.getTaal());
                    brief.activeer();
                    documentRepository.save(brief);
                }
        );
    }
}
