package be.artisjaap.polyglot.core.action.translation;

import be.artisjaap.polyglot.core.action.to.TranslationTO;
import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class FindTranslationsNotInLesson {


    public List<TranslationTO> forLanguagePair(String languagePairId){
        return new ArrayList<>();
    }
}
