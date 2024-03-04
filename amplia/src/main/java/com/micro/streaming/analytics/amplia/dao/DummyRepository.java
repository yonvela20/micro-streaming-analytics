package com.micro.streaming.analytics.amplia.dao;

import com.micro.streaming.analytics.amplia.dto.Dummy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DummyRepository extends MongoRepository<Dummy, Integer> {
}
