package be.artisjaap.document.action;

import be.artisjaap.document.model.Document;
import be.artisjaap.document.model.mongo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeactivateDocument {

    private final DocumentRepository documentRepository;

    public DeactivateDocument(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void withCodeAndLanguage(String code, String language) {
        Optional<Document> documentOptional = documentRepository.findByCodeAndTaalAndActief(code, language);
        documentOptional.ifPresent(document -> {
            document.deactivate();
            documentRepository.save(document);
        });

    }
}
