package com.gemini.business.order.controller;

import com.gemini.boot.framework.web.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Value("${server.port}")
    private String port;

    @GetMapping
    public Message test() {
        log.info("order");
        return Message.success("端口：" + port);
    }
}
