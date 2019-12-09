package be.artisjaap.polyglot.web.endpoints;


import be.artisjaap.polyglot.core.action.pairs.CreateLanguagePair;
import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.pairs.RemoveLanguagePair;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewLanguagePairTO;
import be.artisjaap.polyglot.web.endpoints.old.request.LanguagePairRequest;
import be.artisjaap.polyglot.web.endpoints.request.NewLanguagePairRequest;
import be.artisjaap.polyglot.web.endpoints.response.LanguagePairResponse;
import be.artisjaap.polyglot.web.endpoints.response.LanguagePairResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LanguagePairController extends BaseController {

    @Resource
    private FindLanguagePair findLanguagePair;

    @Resource
    private LanguagePairResponseMapper languagePairResponseMapper;

    @Resource
    private CreateLanguagePair createLanguagePair;

    @Resource
    private RemoveLanguagePair removeLanguagePair;


    @RequestMapping(value = "/language-pairs", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LanguagePairResponse>> allLanguagePairs() {
        return ResponseEntity.ok(languagePairResponseMapper.mapToResponse(findLanguagePair.allPairsForUserId(userId())));
    }

    @RequestMapping(value = "/language-pair", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LanguagePairResponse> createLanguagePair(@RequestBody NewLanguagePairRequest languagePairRequest) {
        LanguagePairTO languagePairTO = createLanguagePair.forUser(NewLanguagePairTO.newBuilder()
                .withUserId(userId())
                .withLanguageFrom(languagePairRequest.getLanguageA())
                .withLanguageTo(languagePairRequest.getLanguageB())
                .build());
        return ResponseEntity.ok(languagePairResponseMapper.map(languagePairTO));
    }

    @RequestMapping(value = "/language-pair/{languagePairId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<LanguagePairResponse> createLanguagePair(@PathVariable String languagePairId) {
        LanguagePairTO languagePair = findLanguagePair.byId(languagePairId);
        removeLanguagePair.withId(languagePairId);
        return ResponseEntity.ok(languagePairResponseMapper.map(languagePair));
    }

}
