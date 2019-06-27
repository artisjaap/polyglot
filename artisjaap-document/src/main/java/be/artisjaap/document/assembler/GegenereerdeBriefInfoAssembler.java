package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.GegenereerdeBriefInfoTO;
import be.artisjaap.document.model.GegenereerdeBrief;
import org.springframework.stereotype.Component;

@Component
public class GegenereerdeBriefInfoAssembler implements Assembler<GegenereerdeBrief, GegenereerdeBriefInfoTO> {
    @Override
    public GegenereerdeBrief assembleEntity(GegenereerdeBriefInfoTO gegenereerdeBriefInfoTO) {
        throw new UnsupportedOperationException("Not ready yet");
    }

    @Override
    public GegenereerdeBriefInfoTO assembleTO(GegenereerdeBrief gegenereerdeBrief) {
        return GegenereerdeBriefInfoTO.newBuilder()
                .briefCode(gegenereerdeBrief.getBriefCode())
                .briefLocatieType(gegenereerdeBrief.getDocumentLocation().getType())
                .id(gegenereerdeBrief.getId().toString())
                .taal(gegenereerdeBrief.getTaal())
                .build();
    }
}
