package com.ssg4.be.config.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 스웨거 환경 설정
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String REFERENCE = "Bearer 토큰 값";

	@Bean
	public Docket api() {
		// 접속 url : /swagger-ui/
		return new Docket(DocumentationType.OAS_30)
			.select()
			.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
			.paths(PathSelectors.any())
			.build()
			.securityContexts(List.of(securityContext()))
			.securitySchemes(List.of(bearerAuthSecurityScheme()));
	}

	private SecurityContext securityContext() {
		return springfox.documentation.spi.service.contexts.SecurityContext.builder()
			.securityReferences(defaultAuth())
			.operationSelector(operationContext -> true)
			.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = new AuthorizationScope("global", "accessEverything");
		return List.of(new SecurityReference(REFERENCE, authorizationScopes));
	}

	private HttpAuthenticationScheme bearerAuthSecurityScheme() {
		return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name(REFERENCE).build();
	}

}
