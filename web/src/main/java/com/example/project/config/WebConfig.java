package com.example.project.config;

import com.example.project.utils.URLUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(URLUtils.BASE, URLUtils.BASE2, URLUtils.BASE3, URLUtils.BASE4, URLUtils.BASE5, URLUtils.BASE6, URLUtils.BASE7);
    }
}
