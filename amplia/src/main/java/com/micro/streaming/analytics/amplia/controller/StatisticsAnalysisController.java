package com.micro.streaming.analytics.amplia.controller;

import com.micro.streaming.analytics.amplia.dao.StatisticsAnalysisRepository;
import com.micro.streaming.analytics.amplia.dto.StatisticsAnalysis;
import com.micro.streaming.analytics.amplia.service.StatisticsAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class StatisticsAnalysisController {

    @Autowired
    StatisticsAnalysisService statsService;

    // TODO: Implement web service with more complex query
    @GetMapping("/statistics")
    public ResponseEntity<?> getAllStatistics() {
        return statsService.findAll();
    }

    @GetMapping("/statistics/{id}")
    public ResponseEntity<?> getStatisticsById(@PathVariable(value = "id") String id) {
        return statsService.findById(id);
    }
}
