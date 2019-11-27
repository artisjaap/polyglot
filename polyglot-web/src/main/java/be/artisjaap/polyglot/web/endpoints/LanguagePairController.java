package be.artisjaap.polyglot.web.endpoints;


import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.web.endpoints.response.LanguagePairResponse;
import be.artisjaap.polyglot.web.endpoints.response.LanguagePairResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LanguagePairController extends BaseController {

    @Resource
    private FindLanguagePair findLanguagePair;

    @Resource
    private LanguagePairResponseMapper languagePairResponseMapper;

    @RequestMapping(value = "/language-pairs", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LanguagePairResponse>> allLanguagePairs() {
        return ResponseEntity.ok(languagePairResponseMapper.mapToResponse(findLanguagePair.allPairsForUserId(userId())));
    }
}
