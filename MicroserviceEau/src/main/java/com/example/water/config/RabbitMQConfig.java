package com.example.water.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RabbitMQConfig {
    
    // Queue names
    public static final String SURCONSOMMATION_QUEUE = "surconsommation.queue";
    public static final String SURCONSOMMATION_EXCHANGE = "surconsommation.exchange";
    public static final String SURCONSOMMATION_ROUTING_KEY = "surconsommation.routingkey";
    
    // Declare Queue
    @Bean
    public Queue surconsommationQueue() {
        return new Queue(SURCONSOMMATION_QUEUE, true); // true = durable queue
    }
    
    // Declare Exchange
    @Bean
    public TopicExchange surconsommationExchange() {
        return new TopicExchange(SURCONSOMMATION_EXCHANGE);
    }
    
    // Bind Queue to Exchange
    @Bean
    public Binding binding(Queue surconsommationQueue, TopicExchange surconsommationExchange) {
        return BindingBuilder.bind(surconsommationQueue)
                .to(surconsommationExchange)
                .with(SURCONSOMMATION_ROUTING_KEY);
    }
    
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // ADD THIS
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    
    // Configure RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}