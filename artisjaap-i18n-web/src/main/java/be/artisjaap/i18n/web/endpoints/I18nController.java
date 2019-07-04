package be.artisjaap.i18n.web.endpoints;

import be.artisjaap.i18n.action.I18nFindTranslation;
import be.artisjaap.i18n.web.endpoints.response.TranslationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/i18n")
public class I18nController {

    @Autowired
    private I18nFindTranslation i18nFindTranslation;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<TranslationResponse>> getAllProperties() {
        return ResponseEntity.ok(TranslationResponse.from(i18nFindTranslation.findAllTranslations()));
    }
}
