package com.gemini.business.order;

//import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.gemini")
//nacos 不写这个注解也能注册
@EnableDiscoveryClient
//apollo 一定要写
//@EnableApolloConfig
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
