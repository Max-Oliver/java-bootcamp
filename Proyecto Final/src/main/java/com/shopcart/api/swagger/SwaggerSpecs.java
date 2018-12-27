package com.shopcart.api.swagger;

//import static springfox.documentation.builders.PathSelectors.regex;
//import java.time.LocalDateTime;
//import java.util.Date;
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
 * 
 * @author Maximiliano
 * */
@EnableSwagger2
@Configuration
public class SwaggerSpecs {

    @Bean
  public Docket Api() {
      return new Docket(DocumentationType.SWAGGER_2)
              //.groupName("client/Produdct/Cart")
              .apiInfo(apiInfo())       
              //.directModelSubstitute(LocalDateTime.class, Date.class)
              //.select()
              //.apis(RequestHandlerSelectors.any("com.shopcart.api"))
              //.paths(regex("/*"))
              //.build();
      
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.any())
      .build();
  }
   	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Api_ShopCart4Clients")
				.description("Final Proyect - Bootcamp-Java Globant By Maximiliano Olivero")
				.version("0.1")
				.build();
	}
}
 