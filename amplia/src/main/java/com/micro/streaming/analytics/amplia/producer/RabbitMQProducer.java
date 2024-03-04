package com.micro.streaming.analytics.amplia.producer;

import com.micro.streaming.analytics.amplia.dto.Dummy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;


    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Generate dummy object every 3 seconds
    @Scheduled(fixedDelay = 3000)
    public void sendMessage(){
        // TODO: Generate a proper object following opengate doc
        Dummy dummy = new Dummy();

        dummy.setId(new Random().nextInt(10));
        dummy.setFirstName("new user " + dummy.getId());
        dummy.setLastName("generic info");

        LOGGER.info(String.format("Message sent -> %s", dummy.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, dummy);
    }
}
