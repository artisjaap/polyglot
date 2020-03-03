package be.artisjaap.common.cucumber;

import be.artisjaap.common.utils.LocalDateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.java.ParameterType;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParameterTypes {

    @ParameterType("[A-Z_, ]*")
    public List<String> listOfCodes(String string){
        return Arrays.asList(string.split(",")).
        stream()
        .map(String::trim)
        .collect(Collectors.toList());
    }

    @ParameterType("[A-Za-z._, ]*")
    public List<String> filenameParts(String string){
        return Arrays.asList(string.split(",")).
                stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }



    @ParameterType(".*")
    public String file(String file){
        return file;
    }

    @ParameterType("[A-Z_]*")
    public String code(String code){
        return code;
    }


    @ParameterType(".{3}")
    public String language(String documentLanguage){
        return documentLanguage;
    }

    @ParameterType("[A-Z]{2}")
    public String userLanguage(String i18nUserLanguage){
        return i18nUserLanguage;
    }

    @ParameterType("(\\d{2}-\\d{2}-\\d{4}) ([0-9]{2}):([0-9]{2})")
    public LocalDateTime dateAndTime(String date, String hr, String min){
        return LocalDateUtils.parseDateFromDDMMYYYYString(date).atTime(Integer.parseInt(hr), Integer.parseInt(min));
    }

    @ParameterType("[a-z_]+")
    public String key(String key){
        return key;
    }

    @ParameterType(".*")
    public String value(String value){
        return value;
    }

    @ParameterType("[a-zA-Z/:-_].*")
    public String path(String path){
        return path;
    }

    @ParameterType(".*")
    public String qrData(String data){
        return data;
    }



    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer(headersToProperties = true)
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(Object fromValue, Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }


}
