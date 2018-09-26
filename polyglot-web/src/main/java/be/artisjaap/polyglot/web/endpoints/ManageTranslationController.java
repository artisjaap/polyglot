package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.FindLanguagePair;
import be.artisjaap.polyglot.core.action.RegisterLanguagePair;
import be.artisjaap.polyglot.core.action.to.LanguagePairTO;
import be.artisjaap.polyglot.core.action.to.NewLanguagePairTO;
import be.artisjaap.polyglot.web.endpoints.request.LanguagePairRequest;
import be.artisjaap.polyglot.web.endpoints.response.LanguagePairResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/translations")
public class ManageTranslationController {

    @Autowired
    private FindLanguagePair findLanguagePair;

    @Autowired
    private RegisterLanguagePair registerLanguagePair;

    @RequestMapping(value = "/pairs/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LanguagePairResponse>> findAllLanguagePairsForUser(@PathVariable String userId) {
        return ResponseEntity.ok(findLanguagePair.allPairsForUserId(userId).stream().map(LanguagePairResponse::from).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/pairs", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<?> createNewPairForUser(@RequestBody LanguagePairRequest languagePairRequest) {
        LanguagePairTO languagePairTO = registerLanguagePair.forUser(NewLanguagePairTO.newBuilder()
                .withLanguageFrom(languagePairRequest.getLanguageFrom())
                .withLanguageTo(languagePairRequest.getLanguageTo())
                .withUserId(languagePairRequest.getUserId())
                .build());

        return ResponseEntity.ok(LanguagePairResponse.from(languagePairTO));
    }

}
