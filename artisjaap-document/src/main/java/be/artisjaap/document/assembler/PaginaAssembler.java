package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.PaginaTO;
import be.artisjaap.document.model.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginaAssembler implements Assembler<Page, PaginaTO> {
    @Override
    public Page assembleEntity(PaginaTO paginaTO) {
        return Page.newBuilder()
                .withCode(paginaTO.getCode())
                .withPaginaType(paginaTO.getType())
                .build();
    }

    @Override
    public PaginaTO assembleTO(Page page) {

        return PaginaTO.newBuilder()
                .withCode(page.getCode())
                .withType(page.getPaginaType())
                .build();
    }
}
