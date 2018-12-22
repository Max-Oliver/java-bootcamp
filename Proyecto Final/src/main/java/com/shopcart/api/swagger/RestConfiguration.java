package com.shopcart.api.swagger;
/* Another form to redirect the info of swagger to html file, but no time to proube this.*/

/*
import org.apache.commons.logging.Log;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

 

@Configuration
@EnableWebMvc
@ComponentScan({"org.codesolt"})
public class RestConfiguration extends WebMvcConfigurerAdapter{

	
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("Classpath:/src/main/resources/");
			registry.addResourceHandler("/webjars/**")
			.addResourceLocations("Classpath:/src/main/resources/");
		}
}

/*
@RestController
@RequestMapping(value = "/")
public class LandingController{
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView swaggerUi() {
		//Log.info("Get to Loading page");
		return new ModelAndView("redirect:" + "/swagger-ui.html");
	}
	
}
*/