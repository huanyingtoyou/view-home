package com.lihy.view.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping(value = "/{hello}",method = RequestMethod.GET)
    public String test(@PathVariable(value = "hello") String hello){
        amqpTemplate.convertAndSend("spring-boot", hello + " from RabbitMQ!");
        return  "hello";
    }
}
