package com.ssg4.be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssg4.be.config.interceptor.MainInterceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final MainInterceptor mainInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mainInterceptor)
            // 인증 관련
            .excludePathPatterns("/auth/**")
            // 테스트 관련
            .excludePathPatterns("/test/**")
            // swagger
            .excludePathPatterns(
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/img/**",
                "/v3/api-docs"
            )
            .addPathPatterns("/*/**");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:/home/ubuntu/img/");
    }
}
