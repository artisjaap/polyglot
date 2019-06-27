package be.artisjaap.angular.generator.action;

import be.artisjaap.angular.generator.action.vo.UrlParameterVO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UrlParameterToMother {

    public static UrlParameterVO init(){
        return UrlParameterToMother.initWithParamId(1);
    }

    public static UrlParameterVO initWithParamId(int id){
        return UrlParameterVO.newBuilder()
                .withName("param"+id)
                .withType("string")
                .build();
    }



    public static List<UrlParameterVO> initNumberOfParams(int number) {
        return IntStream.rangeClosed(1, number)
                .mapToObj(x -> UrlParameterToMother.initWithParamId(x))
                .collect(Collectors.toList());


    }
}
