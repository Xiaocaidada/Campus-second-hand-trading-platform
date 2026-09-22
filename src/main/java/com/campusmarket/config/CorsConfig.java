package com.campusmarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 修改点：去掉 './'，直接使用 'file:upload/images/'
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:upload/images/");
        // 如果需要，可以添加多个位置，例如：
        // registry.addResourceHandler("/images/**")
        //         .addResourceLocations("file:upload/images/", "file:/absolute/path/");
    }
}