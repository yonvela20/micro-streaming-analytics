package com.micro.streaming.analytics.amplia.producer;

import com.micro.streaming.analytics.amplia.dto.Datacollection;
import com.micro.streaming.analytics.amplia.dto.Datapoints;
import com.micro.streaming.analytics.amplia.dto.Datastreams;
import com.micro.streaming.analytics.amplia.dto.Dummy;
import jdk.jfr.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
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
    public void generateData() {
        // We create a list of datapoints
        ArrayList<Datapoints> datapointsList = new ArrayList<Datapoints>();

        // We fill a datapoint obj with dummy values
        Datapoints datapoint = new Datapoints();
        datapoint.setAt(128368);
        datapoint.setFrom(128368);
        datapoint.setValue("value");

        // We add the datapoint to the datapoint list
        datapointsList.add(datapoint);

        // We create a list of datastreams
        ArrayList<Datastreams> datastreamsList = new ArrayList<Datastreams>();

        // We fill a datastream obj with dummy values
        Datastreams datastream = new Datastreams();
        datastream.setId("temperature");
        datastream.setFeed("feed_1");
        datastream.setDatapoints(datapointsList);

        // We add a datastream to the datastream
        datastreamsList.add(datastream);

        // Fill the rest of the object
        Datacollection datacollection = new Datacollection();
        datacollection.setVersion("1.0.0");
        datacollection.setDatastreams(datastreamsList);

        LOGGER.info(String.format("New object created -> %s", datacollection.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, datacollection);
    }
}
