package be.artisjaap.document.web;

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
    @Autowired
    private AddTemplate addTemplate;

    @Autowired
    private AddCombinedTemplate addCombinedTemplate;

    @Autowired
    private AddDocument addDocument;

    @Autowired
    private ActivateTemplate activateTemplate;

    @Autowired
    private ActivateCombinedTemplate activateCombinedTemplate;

    @Autowired
    private ActivateDocument activateDocument;

    @Autowired
    private CreatePreview createPreview;

    @RequestMapping(value = "/template", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TemplateCodeResponse>  createNewTemplateType(@RequestBody NewTemplateCodeRequest newTemplateCodeRequest){
        TemplateCodeTO templateCodeTO = addTemplate.metNieuweCode(TemplateCodeNieuwTO.newBuilder()
                .withCode(newTemplateCodeRequest.getCode())
                .withDescription(newTemplateCodeRequest.getDescription())
                .build());
        return ResponseEntity.ok(TemplateCodeResponse.from(templateCodeTO));

    }

    @RequestMapping(value = "/template/upload", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TemplateResponse>  uploadNewTemplate(@RequestBody TemplateRequest newTemplateRequest, @RequestParam MultipartFile file){
        TemplateTO template = addTemplate.voor(TemplateNieuwTO.newBuilder()
                .withCode(newTemplateRequest.getCode())
                .withOriginalFilename(file.getOriginalFilename())
                .withTaal(newTemplateRequest.getLanguage())
                .withTemplate(WebUtils.getBytesFromMultipartFile(file))
                .build());
        return ResponseEntity.ok(TemplateResponse.from(template));

    }

    @RequestMapping(value = "/combined-template", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CombinedTemplateCodeResponse> createNewCombinedTemplateCode(@RequestBody NewCombinedTemplateCodeRequest newCombinedTemplateRequest){
        GecombineerdeTemplateCodeTO combinedTemplateTO = addCombinedTemplate.metNieuweCode(TemplateCodeNieuwTO.newBuilder()
                .withCode(newCombinedTemplateRequest.getCode())
                .withDescription(newCombinedTemplateRequest.getDescription())
                .build());

        return ResponseEntity.ok(CombinedTemplateCodeResponse.from(combinedTemplateTO));

    }

    @RequestMapping(value = "/combined-template/config", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CombinedTemplateResponse> createNewCombinedTemplate(@RequestBody NewCombinedTemplateRequest newCombinedTemplateRequest){
        GecombineerdeTemplateTO uit = addCombinedTemplate.uit(GecombineerdeTemplateNieuwTO.newBuilder()
                .withCode(newCombinedTemplateRequest.getCode())
                .withTaal(newCombinedTemplateRequest.getTaal())
                .withTemplates(newCombinedTemplateRequest.getTemplates())
                .build());

        return ResponseEntity.ok(CombinedTemplateResponse.from(uit));

    }

    @RequestMapping(value = "/document", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<NewDocumentCodeResponse> createNewDocument(@RequestBody NewDocumentCodeRequest newDocumentCodeRequest){
        BriefCodeTO briefCodeTO = addDocument.metNieuweCode(BriefCodeNieuwTO.newBuilder()
                .withCode(newDocumentCodeRequest.getCode())
                .withDescription(newDocumentCodeRequest.getDescription())
                .build());
        return ResponseEntity.ok(NewDocumentCodeResponse.from(briefCodeTO));
    }


    @RequestMapping(value = "/activate/template/{templateId}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> activateTemplate(@PathVariable String templateId){
        activateTemplate.activeerTemplate(templateId);
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
        outputStream.write(template.getData().orElse(null));
        response.flushBuffer();
    }

}
