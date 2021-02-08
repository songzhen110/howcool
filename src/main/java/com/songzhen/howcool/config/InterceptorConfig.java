package com.songzhen.howcool.config;

import com.songzhen.howcool.auth.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcInterceptorConfig
 *
 * @author lucas 2018-07-28
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/**");
    }
    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }
}