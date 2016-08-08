package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/Home.html").setViewName("../static/Home");
        registry.addViewController("/").setViewName("../static/Home");
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/403").setViewName("../static/403");
    }

}