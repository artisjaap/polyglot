package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.PaginaTO;
import be.artisjaap.document.model.Pagina;
import org.springframework.stereotype.Component;

@Component
public class PaginaAssembler implements Assembler<Pagina, PaginaTO> {
    @Override
    public Pagina assembleEntity(PaginaTO paginaTO) {
        return Pagina.newBuilder()
                .withCode(paginaTO.getCode())
                .withPaginaType(paginaTO.getType())
                .build();
    }

    @Override
    public PaginaTO assembleTO(Pagina pagina) {

        return PaginaTO.newBuilder()
                .withCode(pagina.getCode())
                .withType(pagina.getPaginaType())
                .build();
    }
}
