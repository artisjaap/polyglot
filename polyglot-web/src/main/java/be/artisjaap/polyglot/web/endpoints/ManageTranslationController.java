package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.FindLanguagePair;
import be.artisjaap.polyglot.core.action.RegisterLanguagePair;
import be.artisjaap.polyglot.core.action.RegisterTranslation;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.web.endpoints.request.LanguagePairRequest;
import be.artisjaap.polyglot.web.endpoints.request.NewTranslationsForUserRequest;
import be.artisjaap.polyglot.web.endpoints.response.LanguagePairResponse;
import be.artisjaap.polyglot.web.endpoints.response.TranslationsForUserResponse;
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

    @Autowired
    private RegisterTranslation registerTranslation;

    @RequestMapping(value = "/pairs/user/{userId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<LanguagePairResponse>> findAllLanguagePairsForUser(@PathVariable String userId) {
        return ResponseEntity.ok(findLanguagePair.allPairsForUserId(userId).stream().map(LanguagePairResponse::from).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/pairs/{languagePairId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<LanguagePairResponse> findLanguagePairWithId(@PathVariable String languagePairId) {
        return ResponseEntity.ok(LanguagePairResponse.from(findLanguagePair.byId(languagePairId)));
    }

    @RequestMapping(value = "/pairs", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LanguagePairResponse> createNewPairForUser(@RequestBody LanguagePairRequest languagePairRequest) {
        LanguagePairTO languagePairTO = registerLanguagePair.forUser(NewLanguagePairTO.newBuilder()
                .withLanguageFrom(languagePairRequest.getLanguageFrom())
                .withLanguageTo(languagePairRequest.getLanguageTo())
                .withUserId(languagePairRequest.getUserId())
                .build());

        return ResponseEntity.ok(LanguagePairResponse.from(languagePairTO));
    }

    @RequestMapping(value = "/pairs/translations", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TranslationsForUserResponse> createNewTranslationsForExistingPair(@RequestBody NewTranslationsForUserRequest translation) {
        TranslationsForUserTO translationsForUserTO = registerTranslation.forAllWords(NewTranslationForUserTO.newBuilder()
                .withUserId(translation.getUserId())
                .withLanguagePairId(translation.getLanguagePairId())
                .withTranslations(translation.getTranslations().stream().map(t -> NewSimpleTranslationPairTO.newBuilder()
                        .withLanguageFrom(t.getLanguageFrom())
                        .withLanguageTO(t.getLanguageTO())
                        .build()).collect(Collectors.toList()))
                .build());

        return ResponseEntity.ok(TranslationsForUserResponse.from(translationsForUserTO));
    }





}
