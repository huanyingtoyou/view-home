package com.lihy.view.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihy
 * @date 2018/11/07
 */
@RestController
@RequestMapping("/fanoutSend")
public class FanoutSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/{content}")
    public String sendMessage(@PathVariable(value = "content") String content) {
        System.out.println("Send message:" + content);
        this.amqpTemplate.convertAndSend("fanoutExchange", "", content);
        return content;
    }
}
