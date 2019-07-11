package com.mytaxi;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.mytaxi.util.LoggingInterceptor;

@EnableSwagger2
@SpringBootApplication
public class RestWebServiceApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
	SpringApplication.run(RestWebServiceApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new LoggingInterceptor())
		.addPathPatterns("/**");
    }

    @Bean
    public Docket docket() {
	return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage(getClass()
			.getPackage().getName())).paths(PathSelectors.any())
		.build().apiInfo(generateApiInfo());
    }

    private ApiInfo generateApiInfo() {
	return new ApiInfo(
		"SpringBoot 2.0.6 Rest Webservice Test",
		"This is a small Rest Webservice for MyTaxi applicant test",
		"Version 1.0 - mw", "urn:tos", new Contact("Pablo Alves", "", "pablofaalves@gmail.com"),
		"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
    }
}
