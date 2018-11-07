package com.lihy.view.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic转发模式
 * 使用@RabbitListener注解监听
 * @author lihy
 * @date 2018/11/07
 */
@Configuration
public class RabbitMqTopicConfig {
    //接收一个topic
    final static String MESSAGE = "topic.message";
    //接收多个topic
    final static String MESSAGES = "topic.messages";

    @Bean
    public Queue queueMessage() {
        return new Queue(RabbitMqTopicConfig.MESSAGE);
    }

    /*@Bean("queue1")
    public Queue queueMessage() {
        return new Queue(RabbitMqConfig.MESSAGE);
    }*/

    @Bean
    public Queue queueMessages() {
        return new Queue(RabbitMqTopicConfig.MESSAGES);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    /**
     * 这里有个注意点
     * 参数queue的名字要和上面queue方法名一致，否则会报找到bean相关的错误
     * 或者在上面new queue的时候指定queue的bean名字，然后在这里参数queue前面用@Qualifier注解指明上面的queue
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /*@Bean
    Binding bindingExchangeMessage(@Qualifier("queue1") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.message");
    }*/

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        //#表示零个或多个词
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
