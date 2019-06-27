@Injectable
class ${name} {

<#list ngServiceMethods as method>

    ${method.methodName}(<#list method.urlParameters as item>${item.name}:${item.type}<#if item_has_next>, </#if></#list>): Observable<${method.returnType}<#if method.returnsList>[]</#if>> {
    return this.httpClient.${method.requestMethod?lower_case}<${method.returnType}<#if method.returnsList>[]</#if>>(this.apiurl + '${method.endpoint}' );
    }


</#list>

}
