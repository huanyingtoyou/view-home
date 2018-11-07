package com.lihy.view.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout Exchange广播形式
 * @author lihy
 * @date 2018/11/07
 */
@Configuration
public class RabbitMqFanoutConfig {

    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }

    /**
     * 配置广播路由器
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(AMessage).to(exchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(BMessage).to(exchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange exchange) {
        return BindingBuilder.bind(CMessage).to(exchange);
    }
}
