package be.artisjaap.document.utils;

import be.artisjaap.document.api.DatasetConfig;
import be.artisjaap.document.api.DatasetConfigProvider;
import be.artisjaap.document.model.Template;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.document.images.IImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Optional;

@Slf4j
public class XdocUtils {

    private static final Logger logger = LoggerFactory.getLogger(XdocUtils.class);

    public static Optional<byte[]> maakDocument(Template template, DatasetConfigProvider briefConfigTO){
        try (InputStream in = new ByteArrayInputStream(template.getTemplate())) {

            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(
                    in, TemplateEngineKind.Velocity);

            IContext context = buildContext(report, template, briefConfigTO);


            ByteArrayOutputStream out = new ByteArrayOutputStream();
            if(template.getDoctype() == DocType.DOCX){
                Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
                report.convert(context, options, out);
            }else if(template.getDoctype() == DocType.PPTX){
                Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
                report.convert(context, options, out);
            }else if(template.getDoctype() == DocType.ODT){
                Options options = Options.getTo(ConverterTypeTo.PDF);
                report.convert(context, options, out);
            }
            return Optional.of(out.toByteArray());

        }catch (Exception e){
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static IContext buildContext(IXDocReport report, Template t, DatasetConfigProvider briefConfigTO) {
        try {
            FieldsMetadata fieldsMetadata = report.createFieldsMetadata();

            IContext context = report.createContext();

            for(String dataset : briefConfigTO.datasets()){
                logger.info("in document {} found dataset with name {}", t.getCode(), dataset);
                DatasetConfig datasetConfig = briefConfigTO.forDataset(dataset);if(datasetConfig != null) {
                    Object data = datasetConfig.data();
                    context.put(datasetConfig.metaName(), data);

                    if(datasetConfig.isAsList()) {
                        fieldsMetadata.addFieldAsList(datasetConfig.metaName());
                    } else {
                        fieldsMetadata.load(datasetConfig.metaName(), datasetConfig.metaClass(), datasetConfig.isAsList());
                    }
                }
                if(context.get(dataset) == null ){
                    logger.error("Ontbrekende dataset in BriefConfiguratie: " + dataset);
                }
            }

            briefConfigTO.imageFields().forEach(documentImage -> {
                fieldsMetadata.addFieldAsImage(documentImage.getName());
                IImageProvider image = new ByteArrayImageProvider(documentImage.getImage());
                context.put(documentImage.getName(), image);
            });

            return context;

        } catch (XDocReportException e ) {
            e.printStackTrace();

        }
        return null;
    }

}
