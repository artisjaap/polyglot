package be.artisjaap.polyglot.testhelper;

import be.artisjaap.polyglot.core.model.LanguagePair;
import org.bson.types.ObjectId;

public class LanguagePairMother extends AbstractMother {

    public static LanguagePair.Builder initRandom(){
        return LanguagePair.newBuilder()
                .withLanguageFrom(fairy.baseProducer().letterify("F????"))
                .withLanguageTo(fairy.baseProducer().letterify("T????"))
                .withUserId(new ObjectId());
    }
}
