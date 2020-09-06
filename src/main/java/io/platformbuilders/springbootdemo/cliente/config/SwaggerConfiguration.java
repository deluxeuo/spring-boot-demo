package io.platformbuilders.springbootdemo.cliente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class that implements the necessary settings for using Swagger as an API documentation tool.
 *  
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	private String releaseVersion = "1.0.0";
	
	private String apiVersion = "1.0.0";
	
	/**
	 * Method that configure all the endpoint's mapped in the documentation.
	 * 
	 * @return <code>Docket</code> object
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("io.platformbuilders.springbootdemo.cliente.controller"))
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	/**
	 * Method that configure the informations about the API. 
	 * 
	 * @return <code>ApiInfo</code> object
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Plataform Builders")
				.description("Plataform Builders Java API - Endpoint's documentation").version(releaseVersion.concat("_").concat(apiVersion))
				.build();
	}
	
}