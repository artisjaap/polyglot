${methodName}(<#list urlParameters as item>${item.name}:${item.type}<#if item_has_next>, </#if></#list>): Observable<${returnType}<#if returnsList>[]</#if>> {
  return this.httpClient.${requestMethod?lower_case}<${returnType}<#if returnsList>[]</#if>>(this.apiurl + '${endpoint}' );
}
