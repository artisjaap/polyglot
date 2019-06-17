package be.artisjaap.angular.generator.action.vo;

import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class ServiceMethodVO {

    private String methodName;
    private String returnType;
    private Boolean returnsList;
    private String endpoint;
    private RequestMethod requestMethod;
    private List<UrlParameterVO> urlParameters;
    private String bodyType;


}


//        allLanguagePairs(): Observable<LanguagePairResponse[]> {
//    const user = this.authenticationService.user;
//            return this.httpClient.get<LanguagePairResponse[]>(this.apiurl + 'api/translations/pairs/user/' + user.userId);
//        }

//        ${methodName}(${urlParameters}): Observable<${returnType}> {
//    const user = this.authenticationService.user;
//            return this.httpClient.${requestMethod}<${returnType}>(this.apiurl + '${endpoint}' + ${urlParameters});
//        }