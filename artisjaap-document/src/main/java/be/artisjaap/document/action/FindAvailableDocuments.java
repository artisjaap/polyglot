package be.artisjaap.document.action;

import be.artisjaap.document.action.to.DocumentTO;
import be.artisjaap.document.assembler.DocumentAssembler;
import be.artisjaap.document.model.mongo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindAvailableDocuments {

    private final DocumentRepository documentRepository;
    private final DocumentAssembler documentAssembler;

    public FindAvailableDocuments(DocumentRepository documentRepository, DocumentAssembler documentAssembler) {
        this.documentRepository = documentRepository;
        this.documentAssembler = documentAssembler;
    }

    public List<DocumentTO> all() {
        return documentAssembler.assembleTO(documentRepository.findAll());
    }

    public List<DocumentTO> withCodeAndLanguage(String code, String language) {
        return documentAssembler.assembleTO(documentRepository.findByCodeAndTaal(code, language));
    }

    public Optional<DocumentTO> activeWithCodeAndLanguage(String code, String language) {
        return documentRepository.findByCodeAndTaalAndActief(code, language).map(documentAssembler::assembleTO);
    }


    public List<DocumentTO> withCode(String code) {
        return documentAssembler.assembleTO(documentRepository.findByCode(code));
    }
}
