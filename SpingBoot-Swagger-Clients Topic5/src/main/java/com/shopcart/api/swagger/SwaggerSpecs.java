package com.shopcart.api.swagger;

import static springfox.documentation.builders.PathSelectors.regex;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Maximiliano
 * */
@Configuration
@EnableSwagger2
public class SwaggerSpecs {

    @Bean
  public Docket newsApi() {
      return new Docket(DocumentationType.SWAGGER_2)
              .groupName("user")
              //.apiInfo(apiInfo())
              .directModelSubstitute(LocalDateTime.class, Date.class)
              .select()
              .paths(regex("/user.*"))
              .build();
  }
	
}
