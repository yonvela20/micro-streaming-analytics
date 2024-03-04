package com.micro.streaming.analytics.amplia.controller;

import com.micro.streaming.analytics.amplia.dao.DatacollectionRepository;
import com.micro.streaming.analytics.amplia.dto.Datacollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DatacollectionController {

    @Autowired
    DatacollectionRepository repository;

    @PostMapping("/datacollection")
    public Datacollection postDatacollection(@RequestBody Datacollection datacollection) {
        return repository.save(datacollection);
    }

    @GetMapping("/datacollection")
    public List<Datacollection> getAllDatacollection() {
        return repository.findAll();
    }
}
