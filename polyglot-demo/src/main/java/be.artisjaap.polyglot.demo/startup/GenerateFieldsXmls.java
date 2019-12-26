package be.artisjaap.polyglot.demo.startup;

import be.artisjaap.common.utils.FileUtils;
import be.artisjaap.document.action.FindAvailableDocuments;
import be.artisjaap.document.action.GenerateFieldsXml;
import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.migrate.model.scripts.AbstractInitScript;
import be.artisjaap.polyglot.core.action.documents.DatasetProviderFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GenerateFieldsXmls extends AbstractInitScript {
    @Resource
    private GenerateFieldsXml generateFieldsXml;

    @Resource
    private FindAvailableDocuments findAvailableDocuments;


    @Override
    public String omschrijving() {
        return "Build document xmls fields";
    }

    @Override
    public String getVersion() {
        return "0.2";
    }

    @Override
    public Integer cardinality() {
        return 100;
    }

    @Override
    public void execute() {
        byte[] bytes = generateFieldsXml.voorDatasets(DatasetProviderFactory.create().addAllDummy());
        FileUtils.writeBytesToTempFile("fields.xml", bytes);
    }
}
