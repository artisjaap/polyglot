package be.artisjaap.document.assembler;

import be.artisjaap.document.action.to.TemplateNieuwTO;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.utils.DatasetInfo;
import be.artisjaap.document.utils.DocType;
import be.artisjaap.document.utils.DocXAnalyser;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
public class TemplateNieuwAssembler implements Assembler<Template, TemplateNieuwTO> {
    @Override
    public Template assembleEntity(TemplateNieuwTO templateTO) {
        DocType docType = DocType.fromFilename(templateTO.getOriginalFilename());
        DatasetInfo mergeInfo = DocXAnalyser.findFields(docType, new ByteArrayInputStream(templateTO.getTemplate()));
        return Template.newBuilder()
                .withActief(false)
                .withCode(templateTO.getCode())
                .withOriginalFilename(templateTO.getOriginalFilename())
                .withTaal(templateTO.getTaal())
                .withTemplate(templateTO.getTemplate())
                .withDoctype(docType)
                .withDatasets(mergeInfo.getDatasets())
                .build();
    }

    @Override
    public TemplateNieuwTO assembleTO(Template template) {
        throw new UnsupportedOperationException("Gebruik TemplateAssembler");
    }
}
