package com.jumia.exercise.configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	 @Bean
	    public Docket api() {
	        final HashSet<String> protocolos = new HashSet<String>();
	        protocolos.add("https");
	        protocolos.add("http");

	        return new Docket(DocumentationType.SWAGGER_2).select().//
	                apis(RequestHandlerSelectors.basePackage("com.jumia.exercise.controller")).//
	                paths(PathSelectors.any()).build().protocols(protocolos).apiInfo(apiInfo());

	    }

	    private ApiInfo apiInfo() {
	        @SuppressWarnings("rawtypes")
	        final List<VendorExtension> col = new ArrayList<VendorExtension>();

	        final ApiInfo apiInfo = new ApiInfo("REST API Jumia", "API for Jumia Excercise", "API 1.0", "", new Contact("Rafael Ferreira", "https://rafaelferreira.dev/", "rafah.26ferreira@hotmail.com"), "", "");

	        return apiInfo;
	    }

}
