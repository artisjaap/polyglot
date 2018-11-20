package be.artisjaap.document.persisters;

import be.artisjaap.document.action.ActiveerBrief;
import be.artisjaap.document.action.AutofindTemplateType;
import be.artisjaap.document.action.MaakBrief;
import be.artisjaap.document.action.to.BriefNieuwTO;
import be.artisjaap.document.action.to.BriefTO;
import be.aclvb.framework.common.plugin.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Component
public class BriefPersister {

    @Resource
    private MaakBrief maakBrief;

    @Resource
    private TemplatePersister templatePersister;

    @Autowired
    private AutofindTemplateType autofindTemplateType;

    @Autowired
    private GecombineerdeTemplatePersister gecombineerdeTemplatePersister;


    @Autowired
    private ActiveerBrief activeerBrief;

    public void maakEenBrief(String code){
        templatePersister.maakTemplate("STANDAARD_HEADER_FOOTER", "STANDAARD_HEADER_FOOTER.DOCX");
        templatePersister.maakTemplate("TEST_TEMPLATE", "MANDAAT_ACTIELIJST_RAPPEL-PROCEDURE_NL.DOCX");
        gecombineerdeTemplatePersister.maakGecombineerdeTemplate("TEST_MET_HEADER_FOOTER", "STANDAARD_HEADER_FOOTER", "TEST_TEMPLATE");

        BriefNieuwTO briefTO = BriefNieuwTO.newBuilder()
                .withCode(code)
                .withTemplates(Arrays.asList("TEST_MET_HEADER_FOOTER", "MANDAAT_STOPZETTING"))
                .withTaal(Language.DUTCH)
                .build();
        BriefTO gemaakteBrief = maakBrief.voor(briefTO);
        activeerBrief.metId(gemaakteBrief.getId());


    }


    public void maakEenSimpleBrief(String code){
        templatePersister.maakTemplate("STANDAARD_HEADER_FOOTER", "STANDAARD_HEADER_FOOTER.DOCX");

        BriefNieuwTO briefTO = BriefNieuwTO.newBuilder()
                .withCode(code)
                .withTemplates(Arrays.asList("STANDAARD_HEADER_FOOTER"))
                .withTaal(Language.DUTCH)
                .build();
        maakBrief.voor(briefTO);
    }
}
