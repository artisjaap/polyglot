package be.artisjaap.polyglot.testhelper;


import be.artisjaap.polyglot.core.model.Translation;
import org.bson.types.ObjectId;

public class TranslationMother extends AbstractMother{


    public static Translation.Builder initRandom(){
        return Translation.newBuilder()
                .withLanguageA(fairy.textProducer().word(1))
                .withLanguageB(fairy.textProducer().word(1))
                .withLanguagePairId(new ObjectId())
                ;
    }
}
