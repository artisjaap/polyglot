package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.TemplateTO;
import be.artisjaap.document.model.Template;
import org.springframework.stereotype.Component;

@Component
public class TemplateAssembler implements Assembler<Template, TemplateTO> {
    @Override
    public Template assembleEntity(TemplateTO templateTO) {
        throw new UnsupportedOperationException("Not ready yet");
    }

    public TemplateTO assembleTO(Template template, boolean includeTemplateBytes) {
        return TemplateTO.newBuilder()
                .withCode(template.getCode())
                .withOriginalFilename(template.getOriginalFilename())
                .withId(template.getTaal())
                .withTemplate(includeTemplateBytes?template.getTemplate():null)
                .withId(template.getId().toString())
                .withActief(template.getActief())
                .withAangemaaktOp(template.getTimeStamp())
                .withTaal(template.getTaal())
                .build();
    }

    @Override
    public TemplateTO assembleTO(Template template) {
        return assembleTO(template, true);
    }
}