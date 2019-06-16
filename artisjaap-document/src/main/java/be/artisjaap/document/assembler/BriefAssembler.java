package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.BriefTO;
import be.artisjaap.document.model.Brief;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BriefAssembler implements Assembler<Brief, BriefTO> {

    @Autowired
    private PaginaAssembler paginaAssembler;

    @Override
    public Brief assembleEntity(BriefTO briefTO) {
        return Brief.newBuilder()
                .withPaginas(paginaAssembler.assembleEntity(briefTO.getPaginas()))
                .withTaal(briefTO.getTaal())
                .withCode(briefTO.getNaam())
                .withActief(false)
                .withType(briefTO.getType())
                .build();


    }

    @Override
    public BriefTO assembleTO(Brief brief) {
        return BriefTO.newBuilder()
                .withPaginas(paginaAssembler.assembleTO(brief.getPaginas()))
                .withTaal(brief.getTaal())
                .withCode(brief.getCode())
                .withId(brief.getId().toString())
                .withType(brief.getType())
                .withActief(brief.getActief())
                .withAangemaaktOp(brief.getTimeStamp())
                .build();


    }
}
