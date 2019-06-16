package be.artisjaap.document.assembler;

import be.artisjaap.document.action.AutofindTemplateType;
import be.artisjaap.document.action.to.BriefNieuwTO;
import be.artisjaap.document.action.to.PaginaTO;
import be.artisjaap.document.model.Brief;
import be.artisjaap.document.model.Pagina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BriefNieuwAssembler implements Assembler<Brief, BriefNieuwTO> {
    @Autowired
    private PaginaAssembler paginaAssembler;

    @Autowired
    private AutofindTemplateType autofindTemplateType;


    @Override
    public Brief assembleEntity(BriefNieuwTO briefNieuwTO) {

        List<Pagina> paginas = briefNieuwTO.getPaginas()
                .stream()
                .map(pagina -> PaginaTO.newBuilder().withCode(pagina).withType(autofindTemplateType.voorPaginaCode(pagina)).build())
                .map(paginaAssembler::assembleEntity)
                .collect(Collectors.toList());


        return Brief.newBuilder()
                .withPaginas(paginas)
                .withTaal(briefNieuwTO.getTaal())
                .withCode(briefNieuwTO.getCode())
                .withActief(false)
                .build();
    }

    @Override
    public BriefNieuwTO assembleTO(Brief brief) {
        throw new UnsupportedOperationException("Not ready yet");
    }
}
