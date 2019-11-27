package be.artisjaap.polyglot.web.endpoints.old;

import be.artisjaap.polyglot.core.action.to.PagedTO;
import be.artisjaap.polyglot.core.action.to.SortTO;
import be.artisjaap.polyglot.core.action.to.TranslationFilterTO;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.action.translation.FindTranslationsFiltered;
import be.artisjaap.polyglot.web.endpoints.old.response.TranslationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

//@RestController
//@RequestMapping("/api")
public class TranslationController {

    @Resource
    private FindTranslationsFiltered findTranslationsFiltered;

    @RequestMapping(path="/translations", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<TranslationResponse>> allMyTranslations(@RequestParam String languagePairId) {
        PagedTO<TranslationTO> translationTOPagedTO = this.findTranslationsFiltered.withFilter(TranslationFilterTO.newBuilder()
                .withLanguagePairId(languagePairId)
                .withOrderByFields(Collections.singletonList(SortTO.newBuilder().withFieldName("_id")
                        .withDirection(SortTO.Direction.DESCENDING).build()))
                .build());
        return ResponseEntity.ok(TranslationResponse.from(translationTOPagedTO.data()));

    }
}
