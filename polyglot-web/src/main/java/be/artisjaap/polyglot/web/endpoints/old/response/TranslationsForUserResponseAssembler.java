package be.artisjaap.polyglot.web.endpoints.old.response;

import be.artisjaap.polyglot.core.action.lesson.FindPracticeWord;
import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.TranslationTO;
import be.artisjaap.polyglot.core.action.to.TranslationsForUserTO;
import be.artisjaap.polyglot.core.action.to.test.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TranslationsForUserResponseAssembler {

    @Autowired
    private FindPracticeWord findPracticeWord;

    public TranslationsForUserResponse assembleResponse(TranslationsForUserTO translationsForUserTO){
        List<PracticeWordTO> practiceWordTOS = findPracticeWord.forTranslations(translationsForUserTO.translations().stream().map(TranslationTO::id).collect(Collectors.toList()), OrderType.NORMAL);
        return TranslationsForUserResponse.from(translationsForUserTO, practiceWordTOS);

    }

}
