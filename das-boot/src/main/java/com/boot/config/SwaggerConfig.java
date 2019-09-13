package com.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {
	private static final String SWAGGER_API_VERSION = "1.0";
	private static final String LICENSE_TEXT = "License";
	private static final String title = "SMS REST API";
	private static final String description = "RestFul API for SMS System";

private ApiInfo apiInfo() {
	return new ApiInfoBuilder().title(title).description(description).version(SWAGGER_API_VERSION)
			.license(LICENSE_TEXT).build();
	}

@Bean
public Docket smsApi() {
	return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).pathMapping("/").select()
					.paths(PathSelectors.regex("/.*")).build();

	}
}
