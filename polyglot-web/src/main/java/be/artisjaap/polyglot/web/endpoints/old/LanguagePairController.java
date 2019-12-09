package be.artisjaap.polyglot.web.endpoints.old;

import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.pairs.CreateLanguagePair;
import be.artisjaap.polyglot.core.action.pairs.RemoveLanguagePair;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewLanguagePairTO;
import be.artisjaap.polyglot.web.endpoints.old.request.LanguagePairRequest;
import be.artisjaap.polyglot.web.endpoints.old.response.LanguagePairResponse;
import be.artisjaap.polyglot.web.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//@RestController
//@RequestMapping("/api")
public class LanguagePairController {

    @Resource
    private FindLanguagePair findLanguagePair;

    @Resource
    private CreateLanguagePair createLanguagePair;

    @Resource
    private RemoveLanguagePair removeLanguagePair;

    @RequestMapping(path = "/languagepairs", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LanguagePairResponse>> findAllForUser() {
        return ResponseEntity.ok(LanguagePairResponse.from(findLanguagePair.allPairsForUserId(SecurityUtils.userId())));
    }

    @RequestMapping(path = "/languagepair", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LanguagePairResponse> creaetNew(@RequestBody LanguagePairRequest languagePairRequest) {
        LanguagePairTO languagePairTO = createLanguagePair.forUser(NewLanguagePairTO.newBuilder()
                .withLanguageFrom(languagePairRequest.getLanguageFrom())
                .withLanguageTo(languagePairRequest.getLanguageTo())
                .withUserId(SecurityUtils.userId())
                .build());

        return ResponseEntity.ok(LanguagePairResponse.from(languagePairTO));
    }

    @RequestMapping(path = "/languagepair/{languagePairId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable String languagePairId){
        removeLanguagePair.withId(languagePairId);
        return ResponseEntity.ok().build();
    }

}
