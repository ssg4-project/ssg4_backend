//package com.ssg4.be.config;
//
//import com.ssg4.be.config.interceptor.MainInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MainInterceptor())
//                // 인증 관련
//                .excludePathPatterns("/auth/**")
//                // swagger
//                .excludePathPatterns(
//                        "/swagger-resources/**",
//                        "/swagger-ui/**",
//                        "/v2/api-docs")
//                .addPathPatterns("/*/**");
//    }
//}
