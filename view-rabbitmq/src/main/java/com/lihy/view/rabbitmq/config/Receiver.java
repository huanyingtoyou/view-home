package com.lihy.view.rabbitmq.config;

import java.util.concurrent.CountDownLatch;

/**
 * 接收者
 * @author lihy
 * @date 2018/11/07
 */
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
