package be.artisjaap.document.web.endpoints;

import be.artisjaap.common.utils.WebUtils;
import be.artisjaap.document.action.*;
import be.artisjaap.document.action.to.*;
import be.artisjaap.document.web.endpoints.request.*;
import be.artisjaap.document.web.endpoints.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/api/document")
public class DocumentController {
    private final AddSimpleTemplate addSimpleTemplate;

    private final AddCombinedTemplate addCombinedTemplate;

    private final AddDocument addDocument;

    private final ActivateSimpleOrCombinedTemplate activateSimpleTemplate;

    private final ActivateCombinedTemplate activateCombinedTemplate;

    private final ActivateDocument activateDocument;

    private final CreatePreview createPreview;

    public DocumentController(AddSimpleTemplate addSimpleTemplate, AddCombinedTemplate addCombinedTemplate, AddDocument addDocument, ActivateSimpleOrCombinedTemplate activateSimpleTemplate, ActivateCombinedTemplate activateCombinedTemplate, ActivateDocument activateDocument, CreatePreview createPreview){
        this.addSimpleTemplate = addSimpleTemplate;
        this.addCombinedTemplate = addCombinedTemplate;
        this.addDocument = addDocument;
        this.activateSimpleTemplate = activateSimpleTemplate;
        this.activateCombinedTemplate = activateCombinedTemplate;
        this.activateDocument = activateDocument;
        this.createPreview = createPreview;
    }

    @RequestMapping(value = "/template", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TemplateCodeResponse>  createNewTemplateType(@RequestBody NewTemplateCodeRequest newTemplateCodeRequest){
        TemplateCodeTO templateCodeTO = addSimpleTemplate.withNewCode(TemplateCodeNewTO.builder()
                .code(newTemplateCodeRequest.getCode())
                .description(newTemplateCodeRequest.getDescription())
                .build());
        return ResponseEntity.ok(TemplateCodeResponse.from(templateCodeTO));

    }

    @RequestMapping(value = "/template/upload", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TemplateResponse>  uploadNewTemplate(@RequestBody TemplateRequest newTemplateRequest, @RequestParam MultipartFile file){
        TemplateTO template = addSimpleTemplate.forNew(TemplateNewTO.builder()
                .code(newTemplateRequest.getCode())
                .originalFilename(file.getOriginalFilename())
                .taal(newTemplateRequest.getLanguage())
                .template(WebUtils.getBytesFromMultipartFile(file))
                .build());
        return ResponseEntity.ok(TemplateResponse.from(template));

    }

    @RequestMapping(value = "/combined-template", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CombinedTemplateCodeResponse> createNewCombinedTemplateCode(@RequestBody NewCombinedTemplateCodeRequest newCombinedTemplateRequest){
        CombinedTemplateCodeTO combinedTemplateTO = addCombinedTemplate.withNewCode(TemplateCodeNewTO.builder()
                .code(newCombinedTemplateRequest.getCode())
                .description(newCombinedTemplateRequest.getDescription())
                .build());

        return ResponseEntity.ok(CombinedTemplateCodeResponse.from(combinedTemplateTO));

    }

    @RequestMapping(value = "/combined-template/config", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CombinedTemplateResponse> createNewCombinedTemplate(@RequestBody NewCombinedTemplateRequest newCombinedTemplateRequest){
        CombinedTemplateTO uit = addCombinedTemplate.from(CombinedTemplateNewTO.builder()
                .code(newCombinedTemplateRequest.getCode())
                .taal(newCombinedTemplateRequest.getTaal())
                .templates(newCombinedTemplateRequest.getTemplates())
                .build());

        return ResponseEntity.ok(CombinedTemplateResponse.from(uit));

    }

    @RequestMapping(value = "/document", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<NewDocumentCodeResponse> createNewDocument(@RequestBody NewDocumentCodeRequest newDocumentCodeRequest){
        BriefCodeTO briefCodeTO = addDocument.metNieuweCode(BriefCodeNieuwTO.builder()
                .code(newDocumentCodeRequest.getCode())
                .description(newDocumentCodeRequest.getDescription())
                .build());
        return ResponseEntity.ok(NewDocumentCodeResponse.from(briefCodeTO));
    }


    @RequestMapping(value = "/activate/template/{templateId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> activateTemplate(@PathVariable String templateId){
        activateSimpleTemplate.activateTemplate(templateId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/activate/combined-template/{templateId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> activateCombinedTemplate(@PathVariable String templateId){
        activateCombinedTemplate.activeerTemplate(templateId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/activate/document/{documentId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> activateDocument(@PathVariable String documentId){
        activateDocument.metId(documentId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/preview/document/{documentId}", method = RequestMethod.GET)
    public void previewDocument(HttpServletResponse response,  @PathVariable String documentId) throws IOException {
        TemplateDataTO template = createPreview.forDocument(documentId);
        OutputStream outputStream = WebUtils.maakOutputstreamVoorFile(response, template.getCode(), "pdf");
        outputStream.write(template.getData());
        response.flushBuffer();
    }

}
