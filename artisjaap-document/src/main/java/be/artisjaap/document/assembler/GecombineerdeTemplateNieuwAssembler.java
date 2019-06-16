package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.GecombineerdeTemplateNieuwTO;
import be.artisjaap.document.model.GecombineerdeTemplate;
import org.springframework.stereotype.Component;

@Component
public class GecombineerdeTemplateNieuwAssembler implements Assembler<GecombineerdeTemplate, GecombineerdeTemplateNieuwTO> {


    @Override
    public GecombineerdeTemplate assembleEntity(GecombineerdeTemplateNieuwTO gecombineerdeTemplateNieuwTO) {
        return GecombineerdeTemplate.newBuilder()
                .withNaam(gecombineerdeTemplateNieuwTO.code())
                .withTaal(gecombineerdeTemplateNieuwTO.taal())
                .withTemplates(gecombineerdeTemplateNieuwTO.templates())
                .build();
    }

    @Override
    public GecombineerdeTemplateNieuwTO assembleTO(GecombineerdeTemplate gecombineerdeTemplateAssembler) {
        return null;
    }
}
