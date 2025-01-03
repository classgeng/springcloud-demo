package com.demo;

import com.demo.mq.channel.ConsumerSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 启动程序
 * 
 * @author xfgeng
 */
@Slf4j
@EnableBinding(ConsumerSink.class)
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ConsumerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(ConsumerApplication.class);
    }

    public static void main(String[] args){
        SpringApplication.run(ConsumerApplication.class, args);
    }
}