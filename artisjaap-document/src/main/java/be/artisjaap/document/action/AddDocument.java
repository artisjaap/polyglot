package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefCodeNieuwTO;
import be.artisjaap.document.action.to.BriefCodeTO;
import be.artisjaap.document.action.to.DocumentNewTO;
import be.artisjaap.document.action.to.DocumentTO;
import be.artisjaap.document.assembler.DocumentAssembler;
import be.artisjaap.document.assembler.DocumentCodeAssembler;
import be.artisjaap.document.assembler.DocumentCodeNewAssembler;
import be.artisjaap.document.assembler.DocumentNewAssembler;
import be.artisjaap.document.model.Document;
import be.artisjaap.document.model.mongo.DocumentCodeRepository;
import be.artisjaap.document.model.mongo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddDocument {

    private final DocumentNewAssembler documentNewAssembler;
    private final DocumentAssembler documentAssembler;
    private final DocumentRepository documentRepository;
    private final DocumentCodeRepository documentCodeRepository;
    private final DocumentCodeNewAssembler documentCodeNewAssembler;
    private final DocumentCodeAssembler documentCodeAssembler;

    public AddDocument(DocumentNewAssembler documentNewAssembler, DocumentAssembler documentAssembler, DocumentRepository documentRepository, DocumentCodeRepository documentCodeRepository, DocumentCodeNewAssembler documentCodeNewAssembler, DocumentCodeAssembler documentCodeAssembler) {
        this.documentNewAssembler = documentNewAssembler;
        this.documentAssembler = documentAssembler;
        this.documentRepository = documentRepository;
        this.documentCodeRepository = documentCodeRepository;
        this.documentCodeNewAssembler = documentCodeNewAssembler;
        this.documentCodeAssembler = documentCodeAssembler;
    }

    public DocumentTO forNew(DocumentNewTO briefTO) {
        Document document = documentNewAssembler.assembleEntity(briefTO);
        document = documentRepository.save(document);
        return documentAssembler.assembleTO(document);
    }

    public BriefCodeTO metNieuweCode(BriefCodeNieuwTO templateCode) {
        return documentCodeAssembler.assembleTO(documentCodeRepository.save(documentCodeNewAssembler.assembleEntity(templateCode)));

    }
}
