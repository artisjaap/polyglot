package be.artisjaap.document.assembler;

import be.artisjaap.document.action.AutofindTemplateType;
import be.artisjaap.document.action.to.DocumentNewTO;
import be.artisjaap.document.action.to.PaginaTO;
import be.artisjaap.document.model.Document;
import be.artisjaap.document.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentNewAssembler implements Assembler<Document, DocumentNewTO> {
    @Autowired
    private PaginaAssembler paginaAssembler;

    @Autowired
    private AutofindTemplateType autofindTemplateType;


    @Override
    public Document assembleEntity(DocumentNewTO documentNewTO) {

        List<Page> pages = documentNewTO.getTemplates()
                .stream()
                .map(pagina -> PaginaTO.builder().code(pagina).type(autofindTemplateType.forPageCode(pagina)).build())
                .map(paginaAssembler::assembleEntity)
                .collect(Collectors.toList());


        return Document.newBuilder()
                .withPaginas(pages)
                .withTaal(documentNewTO.getTaal())
                .withCode(documentNewTO.getCode())
                .withActief(false)
                .build();
    }

    @Override
    public DocumentNewTO assembleTO(Document document) {
        throw new UnsupportedOperationException("Not ready yet");
    }
}
