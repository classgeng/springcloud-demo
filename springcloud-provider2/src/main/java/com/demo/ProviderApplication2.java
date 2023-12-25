package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动程序
 * 
 * @author xfgeng
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderApplication2 extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(ProviderApplication2.class);
    }

    public static void main(String[] args){
        SpringApplication.run(ProviderApplication2.class, args);
    }
}