package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.pairs.FindLanguagePair;
import be.artisjaap.polyglot.core.action.pairs.RegisterLanguagePair;
import be.artisjaap.polyglot.core.action.pairs.RemoveLanguagePair;
import be.artisjaap.polyglot.core.action.to.*;
import be.artisjaap.polyglot.core.action.translation.RegisterTranslation;
import be.artisjaap.polyglot.core.action.translation.UpdateTranslation;
import be.artisjaap.polyglot.web.endpoints.request.LanguagePairRequest;
import be.artisjaap.polyglot.web.endpoints.request.NewTranslationsForUserRequest;
import be.artisjaap.polyglot.web.endpoints.request.UpdateTranslationRequest;
import be.artisjaap.polyglot.web.endpoints.response.LanguagePairResponse;
import be.artisjaap.polyglot.web.endpoints.response.PracticeWordResponse;
import be.artisjaap.polyglot.web.endpoints.response.TranslationsForUserResponse;
import be.artisjaap.polyglot.web.endpoints.response.TranslationsForUserResponseAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
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

    @Autowired
    private RemoveLanguagePair removeLanguagePair;

    @Autowired
    private TranslationsForUserResponseAssembler translationsForUserResponseAssembler;

    @Autowired
    private UpdateTranslation updateTranslation;

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

    @RequestMapping(value = "/pair/{languagePairId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<LanguagePairResponse> removePairForUser(@PathVariable String languagePairId) {
        ResponseEntity<LanguagePairResponse> languagePairWithId = findLanguagePairWithId(languagePairId);
        removeLanguagePair.withId(languagePairId);
        return languagePairWithId;
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


        return ResponseEntity.ok(translationsForUserResponseAssembler.assembleResponse(translationsForUserTO));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<PracticeWordResponse> updateTranslation(@RequestBody UpdateTranslationRequest updateTranslationRequest){
        updateTranslation.forTranslation(UpdateTranslationTO.newBuilder()
                .withLanguageFrom(updateTranslationRequest.getLanguageFrom())
                .withLanguageTo(updateTranslationRequest.getLanguageTo())
                .withTranslationId(updateTranslationRequest.getTranslationId())
                .build());
        return null; //FIXME
    }

    @RequestMapping(value = "/pairs/{userId}/translations/{languagePairId}/file", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<TranslationsForUserResponse> uploadTranslationsByFile(@PathVariable String userId, @PathVariable String languagePairId, @RequestParam MultipartFile file) {
        System.out.println(file);
        try(InputStreamReader val = new InputStreamReader(file.getInputStream())) {
            TranslationsForUserTO translationsForUserTO = registerTranslation.forAllWords(NewTranslationForUserFromFileTO.newBuilder()
                    .withUserId(userId)
                    .withLanguagePairId(languagePairId)
                    .withReader(val)
                    .build());
            return ResponseEntity.ok(translationsForUserResponseAssembler.assembleResponse(translationsForUserTO));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }




}
