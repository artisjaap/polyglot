package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.action.to.TemplateDataTO;
import be.artisjaap.document.api.DatasetConfigProvider;
import be.artisjaap.document.model.CombinedTemplate;
import be.artisjaap.document.model.Page;
import be.artisjaap.document.model.mongo.CombinedTemplateRepository;
import be.artisjaap.document.utils.PDFUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenerateCombinedTemplate {

    private final GenerateSimpleTemplate generateSimpleTemplate;
    private final CombinedTemplateRepository combinedTemplateRepository;
    private final FindAvailableSimpleTemplates findAvailableSimpleTemplates;

    public GenerateCombinedTemplate(GenerateSimpleTemplate generateSimpleTemplate, CombinedTemplateRepository combinedTemplateRepository, FindAvailableSimpleTemplates findAvailableSimpleTemplates) {
        this.generateSimpleTemplate = generateSimpleTemplate;
        this.combinedTemplateRepository = combinedTemplateRepository;
        this.findAvailableSimpleTemplates = findAvailableSimpleTemplates;
    }


    public TemplateDataTO generate(Page page, BriefConfigTO briefConfig) {

        switch (page.getPaginaType()) {
            case COMBINED:
                return generateCombinedPages(page, briefConfig);
            case TEMPLATE:
                return generateSimpleTemplate.voor(page.getCode(), briefConfig);
        }
        return TemplateDataTO.empty();
    }

    private TemplateDataTO generateCombinedPages(Page page, BriefConfigTO briefConfig) {
        CombinedTemplate template = combinedTemplateRepository.findByCodeAndTaalAndActief(page.getCode(), briefConfig.getTaal()).orElseThrow(() -> new IllegalStateException("template niet gevonden"));
        List<TemplateDataTO> collect = template.getTemplates().stream().map(t -> generateSimpleTemplate.voor(t, briefConfig)).collect(Collectors.toList());
        List<byte[]> docs = collect.stream().map(TemplateDataTO::getData)
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get).collect(Collectors.toList());

        Set<ObjectId> usedTemplates = collect.stream().flatMap(templateDataTO -> templateDataTO.getGebruikteTemplates().stream()).collect(Collectors.toSet());

        byte[] combinedTemplate = PDFUtils.stampDoc(docs);

        return TemplateDataTO.builder()
                .code(page.getCode())
                .data(combinedTemplate)
                .gebruikteTemplates(usedTemplates)
                .build();

    }

    public TemplateDataTO forCombinedTemplateWithId(ObjectId templateId, DatasetConfigProvider to) {
        CombinedTemplate template = combinedTemplateRepository.findById(templateId).orElseThrow(() -> new UnsupportedOperationException("Gecombineerde Template bestaat niet"));
        List<TemplateDataTO> collect = template.getTemplates().stream()
                .map(t -> findAvailableSimpleTemplates.activeWithCodeAndLanguage(t, template.getTaal()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(t -> generateSimpleTemplate.voorTemplateMetId(new ObjectId(t.getId()), to)).collect(Collectors.toList());

        List<byte[]> docs = collect.stream().map(TemplateDataTO::getData)
                .map(Optional::ofNullable)
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        Set<ObjectId> usedTemplates = collect.stream().flatMap(templateDataTO -> templateDataTO.getGebruikteTemplates().stream()).collect(Collectors.toSet());

        byte[] combinedTemplates = PDFUtils.stampDoc(docs);

        return TemplateDataTO.builder()
                .data(combinedTemplates)
                .gebruikteTemplates(usedTemplates)
                .build();


    }
}
