package be.artisjaap.document.web;

import be.artisjaap.document.action.EenvoudigeTemplateToevoegen;
import be.artisjaap.document.action.GecombineerdeTemplateToevoegen;
import be.artisjaap.document.action.MaakBrief;
import be.artisjaap.document.action.to.*;
import be.artisjaap.document.web.endpoints.request.*;
import be.artisjaap.document.web.endpoints.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private EenvoudigeTemplateToevoegen eenvoudigeTemplateToevoegen;

    @Autowired
    private GecombineerdeTemplateToevoegen gecombineerdeTemplateToevoegen;

    @Autowired
    private MaakBrief maakBrief;

    @RequestMapping(value = "/template", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TemplateCodeResponse>  createNewTemplateType(@RequestBody NewTemplateCodeRequest newTemplateCodeRequest){
        TemplateCodeTO templateCodeTO = eenvoudigeTemplateToevoegen.metNieuweCode(TemplateCodeNieuwTO.newBuilder()
                .withCode(newTemplateCodeRequest.getCode())
                .withDescription(newTemplateCodeRequest.getDescription())
                .build());
        return ResponseEntity.ok(TemplateCodeResponse.from(templateCodeTO));

    }

    @RequestMapping(value = "/template/upload", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TemplateResponse>  createNewTemplateType(@RequestBody TemplateRequest newTemplateRequest, @RequestParam MultipartFile file){
        TemplateTO template = eenvoudigeTemplateToevoegen.voor(TemplateNieuwTO.newBuilder()
                .withCode(newTemplateRequest.getCode())
                .withOriginalFilename(file.getOriginalFilename())
                .withTaal(newTemplateRequest.getLanguage())
                .withTemplate(getBytesFromMultipartFile(file))
                .build());
        return ResponseEntity.ok(TemplateResponse.from(template));

    }

    @RequestMapping(value = "/combined-template", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CombinedTemplateCodeResponse> createNewCombinedTemplateCode(@RequestBody NewCombinedTemplateCodeRequest newCombinedTemplateRequest){
        GecombineerdeTemplateCodeTO combinedTemplateTO = gecombineerdeTemplateToevoegen.metNieuweCode(TemplateCodeNieuwTO.newBuilder()
                .withCode(newCombinedTemplateRequest.getCode())
                .withDescription(newCombinedTemplateRequest.getDescription())
                .build());

        return ResponseEntity.ok(CombinedTemplateCodeResponse.from(combinedTemplateTO));

    }

    @RequestMapping(value = "/combined-template/config", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CombinedTemplateResponse> createNewCombinedTemplate(@RequestBody NewCombinedTemplateRequest newCombinedTemplateRequest){
        GecombineerdeTemplateTO uit = gecombineerdeTemplateToevoegen.uit(GecombineerdeTemplateNieuwTO.newBuilder()
                .withCode(newCombinedTemplateRequest.getCode())
                .withTaal(newCombinedTemplateRequest.getTaal())
                .withTemplates(newCombinedTemplateRequest.getTemplates())
                .build());

        return ResponseEntity.ok(CombinedTemplateResponse.from(uit));

    }

    @RequestMapping(value = "/document", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<NewDocumentCodeResponse> createNewCombinedTemplate(@RequestBody NewDocumentCodeRequest newDocumentCodeRequest){
        BriefCodeTO briefCodeTO = maakBrief.metNieuweCode(BriefCodeNieuwTO.newBuilder()
                .withCode(newDocumentCodeRequest.getCode())
                .withOmschrijvingFr(newDocumentCodeRequest.getDescription())
                .build());

        return ResponseEntity.ok(NewDocumentCodeResponse.from(briefCodeTO));

    }

    //FIXME Create new Artisjaap-common
    // create new WebUtils en move method inside
    public static byte[] getBytesFromMultipartFile(MultipartFile file){
        try{
            return file.getBytes();
        }catch(IOException ex){

        }
        return new byte[0];
    }
}
