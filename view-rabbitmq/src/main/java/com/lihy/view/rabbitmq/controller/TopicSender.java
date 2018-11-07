package com.lihy.view.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihy
 * @date 2018/11/07
 */
@RestController
@RequestMapping("/topicSender")
public class TopicSender {
    @Autowired
    AmqpTemplate amqpTemplate;

    @RequestMapping("/topicSend1")
    public String topicSend1() {
        String msg = "my topic 1";
        System.out.println("发送者说：" + msg);
        this.amqpTemplate.convertAndSend("exchange", "topic.message", msg);
        return msg;
    }

    @RequestMapping("/topicSend2")
    public String topicSend2() {
        String msg = "my topic 2";
        System.out.println("发送者说：" + msg);
        this.amqpTemplate.convertAndSend("exchange", "topic.messages", msg);
        return msg;
    }
}
