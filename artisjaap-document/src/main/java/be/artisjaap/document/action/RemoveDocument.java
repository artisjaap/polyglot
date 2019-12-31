package be.artisjaap.document.action;

import be.artisjaap.document.model.mongo.DocumentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemoveDocument {

    private final DocumentRepository documentRepository;

    public RemoveDocument(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void metId(String id) {
        documentRepository.deleteById(new ObjectId(id));
    }
}
