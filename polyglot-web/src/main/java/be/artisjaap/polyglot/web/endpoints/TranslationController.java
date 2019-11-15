package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.translation.FindTranslations;
import be.artisjaap.polyglot.web.endpoints.response.LessonHeaderResponse;
import be.artisjaap.polyglot.web.endpoints.response.TranslationResponse;
import be.artisjaap.polyglot.web.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TranslationController {

    @Resource
    private FindTranslations findTranslations;

    @RequestMapping(path="/translations", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<TranslationResponse>> allMyTranslations() {
        return ResponseEntity.ok(TranslationResponse.from(findTranslations.latestFor(SecurityUtils.userId(), 10)));

    }
}
