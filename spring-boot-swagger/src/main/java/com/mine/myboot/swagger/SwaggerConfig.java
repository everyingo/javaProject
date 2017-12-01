package com.mine.myboot.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		        .groupName("用户API接口文档")  
		        .apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mine.myboot.swagger.controller.user"))
				.paths(PathSelectors.any()).build();
	}
	
	@Bean
	public Docket indexApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		        .groupName("首页API接口文档")  
		        .apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mine.myboot.swagger.controller.index"))
				.paths(PathSelectors.any()).build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("XX系统")
				.description("XX,YY服务")
				.termsOfServiceUrl("http://xx.yy.com")
				.contact(new Contact("xx ", "http://xx.yy.com", "aa@bb.com"))
				.version("1.0").build();
	}
}
