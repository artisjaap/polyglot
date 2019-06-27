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

    @Autowired
    private DocumentNewAssembler documentNewAssembler;

    @Autowired
    private DocumentAssembler documentAssembler;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentCodeRepository documentCodeRepository;

    @Autowired
    private DocumentCodeNewAssembler documentCodeNewAssembler;

    @Autowired
    private DocumentCodeAssembler documentCodeAssembler;


    public DocumentTO forNew(DocumentNewTO briefTO) {
        Document document = documentNewAssembler.assembleEntity(briefTO);
        document = documentRepository.save(document);
        return documentAssembler.assembleTO(document);
    }

    public BriefCodeTO metNieuweCode(BriefCodeNieuwTO templateCode) {
        return documentCodeAssembler.assembleTO(documentCodeRepository.save(documentCodeNewAssembler.assembleEntity(templateCode)));

    }
}
