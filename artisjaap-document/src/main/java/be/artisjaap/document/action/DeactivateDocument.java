package be.artisjaap.document.action;

import be.artisjaap.document.model.Document;
import be.artisjaap.document.model.mongo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeactivateDocument {

    @Autowired
    private DocumentRepository documentRepository;

    public void withCodeAndLanguage(String code, String language) {
        Optional<Document> documentOptional = documentRepository.findByCodeAndTaalAndActief(code, language);
        documentOptional.ifPresent(document -> {
            document.deactivate();
            documentRepository.save(document);
        });

    }
}
