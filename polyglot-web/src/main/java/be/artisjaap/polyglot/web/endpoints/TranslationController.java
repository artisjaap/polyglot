package be.artisjaap.polyglot.web.endpoints;


import be.artisjaap.polyglot.core.action.lesson.UpdateLesson;
import be.artisjaap.polyglot.core.action.to.NewTranslationInLessonTO;
import be.artisjaap.polyglot.core.action.to.NewTranslationTO;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.action.to.UpdateTranslationTO;
import be.artisjaap.polyglot.core.action.translation.CreateTranslation;
import be.artisjaap.polyglot.core.action.translation.RemoveTranslation;
import be.artisjaap.polyglot.core.action.translation.UpdateTranslation;
import be.artisjaap.polyglot.web.endpoints.request.NewTranslationForLessonRequest;
import be.artisjaap.polyglot.web.endpoints.request.UpdateTranslationForLessonRequest;
import be.artisjaap.polyglot.web.endpoints.response.TranslationForLessonResponse;
import be.artisjaap.polyglot.web.endpoints.response.TranslationForLessonResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class TranslationController extends BaseController {

    @Resource
    private CreateTranslation createTranslation;

    @Resource
    private TranslationForLessonResponseMapper translationForLessonResponseMapper;

    @Resource
    private UpdateTranslation updateTranslation;

    @Resource
    private UpdateLesson updateLesson;

    @Resource
    private RemoveTranslation removeTranslation;

    //if no lessonId is given create a tramslation without coupling to a lesson
    @RequestMapping(value = "/translation", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<TranslationForLessonResponse> createNewTranslation(@RequestBody NewTranslationForLessonRequest newTranslationForLessonRequest) {

        if (newTranslationForLessonRequest.translationIsForLesson()) {
            return ResponseEntity.ok(translationForLessonResponseMapper.map(
                    createTranslation.forTranslation(NewTranslationInLessonTO.builder()
                            .languageFrom(newTranslationForLessonRequest.getLanguageA())
                            .languageTO(newTranslationForLessonRequest.getLanguageB())
                            .lessonId(newTranslationForLessonRequest.getLessonId())
                            .build()), newTranslationForLessonRequest.getLessonId()));

        } else {
            TranslationTO translationTO = createTranslation.forTranslation(NewTranslationTO.builder()
                    .languageFrom(newTranslationForLessonRequest.getLanguageA())
                    .languageTo(newTranslationForLessonRequest.getLanguageB())
                    .userId(userId())
                    .languagePairId(newTranslationForLessonRequest.getLanguagePairId())
                    .build());

            return ResponseEntity.ok(translationForLessonResponseMapper.map(translationTO));
        }
    }

    //if no lessonId is given, update the translatino for the ID
    //if a lessonId is given create a new translation and remove the original from the lesson
    @RequestMapping(value = "/translation", method = RequestMethod.PATCH)
    public @ResponseBody
    ResponseEntity<TranslationForLessonResponse> updateATranlsation(@RequestBody UpdateTranslationForLessonRequest updateTranslationForLessonRequest) {
        if(updateTranslationForLessonRequest.translationIsForLesson()){
            updateLesson.removeTranslationsFromLesson(updateTranslationForLessonRequest.getLessonId(), updateTranslationForLessonRequest.getTranslationId());
            TranslationTO translationTO = createTranslation.forTranslation(NewTranslationInLessonTO.builder()
                    .lessonId(updateTranslationForLessonRequest.getLessonId())
                    .languageFrom(updateTranslationForLessonRequest.getLanguageA())
                    .languageTO(updateTranslationForLessonRequest.getLanguageB())
                    .build());
            return ResponseEntity.ok(translationForLessonResponseMapper.map(translationTO, updateTranslationForLessonRequest.getLessonId()));
        } else {
            TranslationTO translationTO = updateTranslation.forTranslation(UpdateTranslationTO.newBuilder()
                    .withLanguageFrom(updateTranslationForLessonRequest.getLanguageA())
                    .withLanguageTo(updateTranslationForLessonRequest.getLanguageB())
                    .withTranslationId(updateTranslationForLessonRequest.getTranslationId())
                    .build());
            return ResponseEntity.ok(translationForLessonResponseMapper.map(translationTO));
        }
        
    }

    //if no lessonId is given, delete the translation completly
    //throw error if translation is already used in lessons
    //if lessonId is given only remove from from the lesson
    @RequestMapping(value = "/translation/{translationId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<TranslationForLessonResponse> removeTranslation(@PathVariable String translationId) {
        TranslationTO translationTO = removeTranslation.withId(translationId);
        return ResponseEntity.ok(translationForLessonResponseMapper.map(translationTO));

    }


}
