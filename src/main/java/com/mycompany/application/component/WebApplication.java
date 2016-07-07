package com.mycompany.application.component;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mycompany.application.component.ApiDocumentationSwagger2.Swagger2Configurer;
import com.mycompany.application.component.security.WebSecurityConfigurer;

//NOTE: @SpringBootApplication is a alternative for @Configuration, @EnableAutoConfiguration and @ComponentScan. Probably you want use @Configuration + @ComponentScan. If you want load xml configuration you can use: @ImportResource annotation. If you want use autoconfiguration, but you can disable a few auto configurations, eg:  @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Configuration
@ComponentScan(basePackages = { "boot.configuration",
		"com.mycompany.application.component.controllers",
		"com.mycompany.application.component.services",
		"com.mycompany.application.component.security",
		"com.mycompany.application.component.exceptions"
		})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class })
@EnableJpaRepositories(basePackages = { "com.mycompany.application.component.repositories" })
@EntityScan(basePackages = {"com.mycompany.application.component.domain"})
@Import({	WebSecurityConfigurer .class,
			Swagger2Configurer.class
			})
//@EnableWebMvc
@SpringBootApplication
//TODO: fix me here : either use @SpringBootApplication or @Configuration
//By default Spring Boot will serve static content from a directory called /static (or /public or /resources or /META-INF/resources) in the classpath or from the root of the ServletContext
public class WebApplication {

	
	
	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(WebApplication.class, args);      
		
		/* To see which beans definitions available, */
		final Logger logger = LoggerFactory.getLogger(WebApplication.class);
		logger.debug("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
        	logger.debug(beanName);
        }
        /* END - To see which beans definitions available */
	}
	
	// for API documentation swagger UI
//	 @Override
//	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//	            registry.addResourceHandler("swagger-ui.html")
//	                    .addResourceLocations("classpath:/META-INF/resources/");
//
//	            registry.addResourceHandler("/webjars/**")
//	                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//	    }
}
