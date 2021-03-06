package com.gemini.business.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Gateway服务网关
 *
 * @author 小明不读书
 * @date 2019-06-26
 */
@ComponentScan("com.gemini")
@EnableDiscoveryClient
//apollo 一定要写
//@EnableApolloConfig
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
