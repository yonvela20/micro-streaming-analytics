package com.micro.streaming.analytics.amplia.consumer;

import com.micro.streaming.analytics.amplia.dao.DatacollectionRepository;
import com.micro.streaming.analytics.amplia.dto.Datacollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Autowired
    DatacollectionRepository repository;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(Datacollection datacollection) {
        // TODO: Get proper data and do statistic analysis
        LOGGER.info(String.format("Received JSON message -> %s", datacollection.toString()));
        repository.save(datacollection);
    }
}
