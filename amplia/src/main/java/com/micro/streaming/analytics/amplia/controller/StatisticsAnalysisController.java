package com.micro.streaming.analytics.amplia.controller;

import com.micro.streaming.analytics.amplia.dao.StatisticsAnalysisRepository;
import com.micro.streaming.analytics.amplia.dto.Datacollection;
import com.micro.streaming.analytics.amplia.dto.StatisticsAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StatisticsAnalysisController {

    @Autowired
    StatisticsAnalysisRepository repository;

    @GetMapping("/statistics")
    public List<StatisticsAnalysis> getAllStatistics() {
        return repository.findAll();
    }
}
