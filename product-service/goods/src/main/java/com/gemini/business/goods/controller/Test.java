package com.gemini.business.goods.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
@RefreshScope
public class Test {

//    private final RestTemplate restTemplate;
//
//    public Test(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @Value("${gemini.pictureUploadPath}")
    private String picture;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping
    public String test() {
        return restTemplate.getForObject("http://order-service" + "/order", String.class);
    }

    @GetMapping("/1")
    @SentinelResource("test1")
    public String test1() {
        return picture;
    }
}
