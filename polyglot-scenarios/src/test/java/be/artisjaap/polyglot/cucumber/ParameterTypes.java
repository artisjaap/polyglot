package be.artisjaap.polyglot.cucumber;

import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.cucumber.types.LanguagePairType;
import io.cucumber.java.ParameterType;

public class ParameterTypes {

    @ParameterType("([a-zA-Z]*)-([a-zA-Z]*)")
    public LanguagePairType languagePair(String from, String to){
        return LanguagePairType.newBuilder()
                .from(from)
                .to(to)
                .build();
    }

    @ParameterType(".*")
    public String lesson(String lesson){
        return lesson;
    }


    @ParameterType(".*")
    public String username(String username){
        return username;
    }

    @ParameterType(".*")
    public OrderType order(String orderType){
        return OrderType.valueOf(orderType);
    }
}
