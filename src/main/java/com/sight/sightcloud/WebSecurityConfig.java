package com.sight.sightcloud;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


//monisha : added the cors disable...
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

               http.authorizeRequests()
                .antMatchers().permitAll()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource());
    }


    @Bean
     CorsConfigurationSource corsConfigurationSource()
    {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("Content-Disposition");
        config.setAllowedMethods(Arrays.asList("POST","PUT","DELETE","GET"));
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}