package com.lihy.view.rabbitmq.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lihy
 * @date 2018/11/07
 */
@Component
public class FanoutReceiver {
    @RabbitListener(queues = "fanout.A")
    public void processA(String str) {
        System.out.println("Receiver A:" + str);
    }

    @RabbitListener(queues = "fanout.B")
    public void processB(String str) {
        System.out.println("Receiver B:" + str);
    }

    @RabbitListener(queues = "fanout.C")
    public void processC(String str) {
        System.out.println("Receiver C:" + str);
    }
}
