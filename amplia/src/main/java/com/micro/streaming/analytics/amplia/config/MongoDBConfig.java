package com.micro.streaming.analytics.amplia.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBConfig {

    @Value("${mongodb.host}")
    private String host;

    @Value("${mongodb.port}")
    private Integer port;

    @Value("${mongodb.user}")
    private String user;

    @Value("${mongodb.pass}")
    private String password;

    @Bean
    MongoClient mongoClient() {
        return MongoClients.create(String.format("mongodb://%s:%s@%s:%s", user, password, host, port));
    }

    @Bean
    MongoOperations mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "altiadb");
    }
}
