package com.micro.streaming.analytics.amplia.producer;

import com.micro.streaming.analytics.amplia.dto.Datacollection;
import com.micro.streaming.analytics.amplia.dto.Datapoints;
import com.micro.streaming.analytics.amplia.dto.Datastreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    // Generate an opengate object every 10 seconds and send it to rabbitmq
    @Scheduled(fixedDelay = 10000)
    public void generateData() {
        Datacollection datacollection = generateDatacollectionObject();

        LOGGER.info(String.format("New object created -> %s", datacollection.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, datacollection);
    }

    private Datacollection generateDatacollectionObject() {
        // We create a list of datapoints
        ArrayList<Datapoints> datapointsList = new ArrayList<Datapoints>();

        // Random iteration just so we can have multiple values
        int iteration = new Random().nextInt(1, 10);
        for (int i = 0; i < iteration; i++) {
            // We fill a datapoint obj with dummy values
            Datapoints datapoint = new Datapoints();
            datapoint.setAt(Instant.now().toEpochMilli());

            // Generate temperature between -10 and 50 Cº
            datapoint.setValue(new Random().nextInt(-10, 51));

            // We add the datapoint to the datapoint list
            datapointsList.add(datapoint);
        }


        // We create a list of datastreams
        ArrayList<Datastreams> datastreamsList = new ArrayList<Datastreams>();

        // We fill a datastream obj with hardcoded values
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

        return datacollection;
    }
}
