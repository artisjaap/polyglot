package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.DocumentTO;
import be.artisjaap.document.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentAssembler implements Assembler<Document, DocumentTO> {

    private final PaginaAssembler paginaAssembler;

    public DocumentAssembler(PaginaAssembler paginaAssembler) {
        this.paginaAssembler = paginaAssembler;
    }

    @Override
    public Document assembleEntity(DocumentTO documentTO) {
        return Document.newBuilder()
                .withPaginas(paginaAssembler.assembleEntity(documentTO.getPaginas()))
                .withTaal(documentTO.getTaal())
                .withCode(documentTO.getCode())
                .withActief(false)
                .withType(documentTO.getType())
                .build();


    }

    @Override
    public DocumentTO assembleTO(Document document) {
        return DocumentTO.builder()
                .paginas(paginaAssembler.assembleTO(document.getPages()))
                .taal(document.getTaal())
                .code(document.getCode())
                .id(document.getId().toString())
                .type(document.getType())
                .actief(document.getActief())
                .aangemaaktOp(document.getTimeStamp())
                .build();


    }
}
