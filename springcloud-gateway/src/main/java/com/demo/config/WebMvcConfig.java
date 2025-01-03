package com.demo.config;

import com.demo.interceptor.GatewayInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GatewayInterceptor())
                .addPathPatterns("/api/*") //拦截路径
                .excludePathPatterns("/*.png","/*.gif"); //排除路径
    }
}
