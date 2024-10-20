package com.thullyoo.owner_catalog.config.rabbitMQ;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String QUEUE_METHOD_1 = "category.create.queue";
    public static final String QUEUE_METHOD_2 = "category.update.queue";
    public static final String QUEUE_METHOD_3 = "category.delete.queue";
    public static final String QUEUE_METHOD_4 = "product.create.queue";
    public static final String QUEUE_METHOD_5 = "product.update.queue";
    public static final String QUEUE_METHOD_6 = "product.delete.queue";
    public static final String EXCHANGE_NAME = "catalog.topic";

    @Bean
    public Queue queueMethod1() {
        return new Queue(QUEUE_METHOD_1, true);
    }

    @Bean
    public Queue queueMethod2() {
        return new Queue(QUEUE_METHOD_2, true);
    }

    @Bean
    public Queue queueMethod3() {
        return new Queue(QUEUE_METHOD_3, true);
    }

    @Bean
    public Queue queueMethod4() {
        return new Queue(QUEUE_METHOD_4, true);
    }

    @Bean
    public Queue queueMethod5() {
        return new Queue(QUEUE_METHOD_5, true);
    }

    @Bean
    public Queue queueMethod6() {
        return new Queue(QUEUE_METHOD_6, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}

