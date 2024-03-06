package com.micro.streaming.analytics.amplia.dao;

import com.micro.streaming.analytics.amplia.dto.StatisticsAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticsAnalysisRepository extends MongoRepository<StatisticsAnalysis, String> {
}
