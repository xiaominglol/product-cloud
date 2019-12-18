package com.gemini.business;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.gemini.boot.framework.core.CoreApplication;
import com.gemini.boot.framework.core.exception.CoreException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.gemini.boot.framework")
//nacos 不写这个注解也能注册
@EnableDiscoveryClient
//apollo 一定要写
@EnableApolloConfig
@SpringBootApplication
public class GoodsApplication {
    public static void main(String[] args) throws CoreException {
        CoreApplication.run(GoodsApplication.class, args);
    }
}
