package com.sight.sightcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAutoConfiguration(exclude = {RedisAutoConfiguration.class,  RabbitAutoConfiguration.class})
@RestController
public class SightcloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SightcloudApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:3000", "https://edmsadmin.azurewebsites.net").maxAge(50000);

				registry.addMapping("/**")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedOrigins("*")
						.allowedHeaders("*")
						.allowCredentials(false);
			}
		};
	}
}