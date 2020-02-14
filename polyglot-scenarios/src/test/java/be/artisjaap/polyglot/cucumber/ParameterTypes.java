package be.artisjaap.polyglot.cucumber;

import be.artisjaap.polyglot.core.action.to.test.OrderType;
import be.artisjaap.polyglot.core.model.LanguagePair;
import be.artisjaap.polyglot.cucumber.types.LanguagePairType;
import io.cucumber.java.ParameterType;

public class ParameterTypes {

    @ParameterType("(.*)-(.*)")
    public LanguagePairType languagePair(String from, String to){
        return LanguagePairType.builder()
                .from(from)
                .to(to)
                .build();
    }

    @ParameterType("(.*)")
    public String username(String username){
        return username;
    }

    @ParameterType("(NORMAL | REVERSE | RANDOM)")
    public OrderType order(String orderType){
        return OrderType.valueOf(orderType);
    }
}
