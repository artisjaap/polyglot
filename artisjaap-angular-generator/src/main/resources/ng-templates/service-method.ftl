${methodName}(): Observable<${returnType}<#if returnsList>[]</#if>> {
  return this.httpClient.${requestMethod}<${returnType}<#if returnsList>[]</#if>>(this.apiurl + '${endpoint}' );
}
