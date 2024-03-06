package com.micro.streaming.analytics.amplia.consumer;

import com.micro.streaming.analytics.amplia.dao.DatacollectionRepository;
import com.micro.streaming.analytics.amplia.dao.StatisticsAnalysisRepository;
import com.micro.streaming.analytics.amplia.dto.Datacollection;
import com.micro.streaming.analytics.amplia.dto.Datastreams;
import com.micro.streaming.analytics.amplia.dto.StatisticsAnalysis;
import com.micro.streaming.analytics.amplia.statistics.StatisticsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RabbitMQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Autowired
    StatisticsAnalysisRepository repository;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumeMessage(Datacollection datacollection) {
        // TODO: Get proper data and do statistic analysis
        LOGGER.info(String.format("Data recieved at %s ", Instant.now()));
        LOGGER.info(String.format("Data looks like this %s", datacollection.toString()));

        ArrayList<Datastreams> datastreams = datacollection.getDatastreams();

        // TODO: Save this object in mongodb
        StatisticsAnalysis stats = createStatisticsObject(datastreams);

        LOGGER.info(String.format("DATOS ---> %s", stats.toString()));

        repository.save(stats);
    }

    private StatisticsAnalysis createStatisticsObject(ArrayList<Datastreams> datastreams) {
        StatisticsAnalysis stats = new StatisticsAnalysis();
        ArrayList<StatisticsAnalysis.DatastreamAnalysis> dsAnalysis = new ArrayList<StatisticsAnalysis.DatastreamAnalysis>();

        datastreams.forEach((Datastreams ds) -> {
            StatisticsAnalysis.DatastreamAnalysis datastreamAnalysis = new StatisticsAnalysis.DatastreamAnalysis();
            datastreamAnalysis.setDatastreamId(ds.getId());
            datastreamAnalysis.setDatastreamFeed(ds.getFeed());

            // Get the values set for the statistics calc
            ArrayList<Integer> valuesSet = new ArrayList<>();
            ds.getDatapoints().forEach(dp -> {
                valuesSet.add(dp.getValue());
            });

            datastreamAnalysis.setCollectedValues(valuesSet);

            datastreamAnalysis.setMedia(StatisticsHelper.getMedia(valuesSet));
            datastreamAnalysis.setMediana(StatisticsHelper.getMediana(valuesSet));
            datastreamAnalysis.setModa(StatisticsHelper.getModa(valuesSet));
            datastreamAnalysis.setDesviacionEstandar(StatisticsHelper.getDesviacionEstandar(valuesSet));
            datastreamAnalysis.setCuartiles(StatisticsHelper.getCuartiles(valuesSet));
            datastreamAnalysis.setMaxValue(StatisticsHelper.getMaxValue(valuesSet));
            datastreamAnalysis.setMinValue(StatisticsHelper.getMinValue(valuesSet));

            // We add the object to the list
            dsAnalysis.add(datastreamAnalysis);
        });

        stats.setDatastreamsAnalysis(dsAnalysis);

        return stats;
    }

//    @RabbitListener(queues = {"${rabbitmq.queue.name}"}, containerFactory = "rabbitListenerContainerFactory")
//    public void consumeBatch(List<Message> datacollectionList) {
//        LOGGER.info(String.format("List size -> %s", datacollectionList.size()));
//        datacollectionList.forEach(item -> {
//            LOGGER.info(String.format("Message received -> %s", item.toString()));
//        });
//    }
}
