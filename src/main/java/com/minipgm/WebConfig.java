package com.minipgm;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //before upload to linux server change the path to "file:/opt/photo/"
        registry.addResourceHandler("/photo/**").addResourceLocations("file:D:/opt/photo/");
        super.addResourceHandlers(registry);
    }

}
