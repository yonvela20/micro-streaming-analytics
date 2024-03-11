package com.micro.streaming.analytics.amplia.service;

import com.micro.streaming.analytics.amplia.dao.StatisticsAnalysisRepository;
import com.micro.streaming.analytics.amplia.dto.StatisticsAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsAnalysisService {

    @Autowired
    StatisticsAnalysisRepository repository;

    public ResponseEntity<?> findAll() {
        ArrayList<StatisticsAnalysis> allStats = (ArrayList<StatisticsAnalysis>) repository.findAll();

        return new ResponseEntity<>(allStats, HttpStatus.OK);
    }

    public ResponseEntity<?> findById(String id) {
        StatisticsAnalysis resp = repository.findById(id).orElse(null);

        Map<String, String> error = new HashMap<>();
        error.put("error", "Element not found");

        if (resp == null) {
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
