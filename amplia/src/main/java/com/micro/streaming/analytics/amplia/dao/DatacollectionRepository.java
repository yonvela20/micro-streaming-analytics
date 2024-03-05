package com.micro.streaming.analytics.amplia.dao;

import com.micro.streaming.analytics.amplia.dto.Datacollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatacollectionRepository extends MongoRepository<Datacollection, String>  {

}
