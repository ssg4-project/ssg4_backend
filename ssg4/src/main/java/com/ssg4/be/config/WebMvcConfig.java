package com.ssg4.be.config;

import com.ssg4.be.config.interceptor.MainInterceptor;
import com.sun.tools.javadoc.Main;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
               // swagger
               .excludePathPatterns(
                       "/swagger-resources/**",
                       "/swagger-ui/**",
                       "/v2/api-docs")
               .addPathPatterns("/*/**");
   }
}
