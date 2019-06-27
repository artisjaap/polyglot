package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.DocumentTO;
import be.artisjaap.document.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentAssembler implements Assembler<Document, DocumentTO> {

    @Autowired
    private PaginaAssembler paginaAssembler;

    @Override
    public Document assembleEntity(DocumentTO documentTO) {
        return Document.newBuilder()
                .withPaginas(paginaAssembler.assembleEntity(documentTO.getPaginas()))
                .withTaal(documentTO.getTaal())
                .withCode(documentTO.getNaam())
                .withActief(false)
                .withType(documentTO.getType())
                .build();


    }

    @Override
    public DocumentTO assembleTO(Document document) {
        return DocumentTO.newBuilder()
                .withPaginas(paginaAssembler.assembleTO(document.getPages()))
                .withTaal(document.getTaal())
                .withCode(document.getCode())
                .withId(document.getId().toString())
                .withType(document.getType())
                .withActief(document.getActief())
                .withAangemaaktOp(document.getTimeStamp())
                .build();


    }
}
