package be.artisjaap.i18n.cucumber;

import io.cucumber.java.ParameterType;

public class ParameterTypes {

    @ParameterType("[a-z\\.]")
    public String translationKey(String file){
        return file;
    }

    @ParameterType(".*")
    public String translation(String file){
        return file;
    }
}
