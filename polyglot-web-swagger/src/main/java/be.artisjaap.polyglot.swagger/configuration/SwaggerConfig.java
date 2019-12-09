package be.artisjaap.polyglot.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api

    @Bean
    public Docket api() {
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder().scope("global").description("full access").build();
        SecurityReference securityReference = SecurityReference.builder().reference("Authorization-Key")
                .scopes(authScopes).build();

        List<SecurityContext> securityContexts = Arrays.asList(
                SecurityContext.builder().securityReferences(Arrays.asList(securityReference)).build());

        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(Arrays.asList(new ApiKey("Authorization-Key", "Authorization", "header")))
                .securityContexts(securityContexts).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Swagger API Security REST API - Polyglot-R")
                .description("\"Swagger API Security REST API - Polyglot-R\"").version("1.0.0").build();
    }
}
