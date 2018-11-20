package be.artisjaap.document.action;

import be.artisjaap.document.action.to.BriefConfigTO;
import be.artisjaap.document.api.DatasetConfig;
import be.artisjaap.document.api.DatasetProvider;
import be.artisjaap.document.model.Template;
import be.artisjaap.document.model.mongo.TemplateRepository;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class GenereerFieldsXml {

    @Autowired
    private TemplateRepository templateRepository;


    public byte[] voorDatasets(DatasetProvider datasets){
        FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            for (String dataset : datasets.datasetNames()) {
                fieldsMetadata.load(dataset, datasets.get(dataset).metaClass());
            }

            fieldsMetadata.saveXML(bos, true);

            bos.flush();

            return bos.toByteArray();

        }catch(Exception ex){
            throw new IllegalStateException("Kan fieldsxml niet maken: " + ex.getMessage(), ex);
        }


    }

    public void voorBrief(BriefConfigTO briefConfig){

        Template template = templateRepository.findByCodeAndTaalAndActief(briefConfig.getCode(), briefConfig.getTaal()).orElseThrow(() -> new UnsupportedOperationException("template niet gevondem"));


        try (InputStream in = new ByteArrayInputStream(template.getTemplate())) {

            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(
                    in, TemplateEngineKind.Velocity);

            FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
            IContext context = report.createContext();

            for(String dataset : briefConfig.getDatasetProvider().datasetNames()){
                DatasetConfig datasetConfig = briefConfig.forDataset(dataset);
                Object data = datasetConfig.data();
                context.put(datasetConfig.metaName(), data);


                fieldsMetadata.load(datasetConfig.metaName(), datasetConfig.metaClass(), datasetConfig.isAsList());

            }

            File xmlFieldsFile = new File("c:/temp/docs/"+briefConfig.getCode()+".fields.xml");
            System.out.println("Save file to " + xmlFieldsFile.getAbsolutePath());
            fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);

        }catch(Exception e){

        }
    }
}
