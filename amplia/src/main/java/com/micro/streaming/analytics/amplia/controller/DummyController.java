package com.micro.streaming.analytics.amplia.controller;

import com.micro.streaming.analytics.amplia.dao.DummyRepository;
import com.micro.streaming.analytics.amplia.dto.Dummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DummyController {

    @Autowired
    private DummyRepository repository;

    @PostMapping("/dummy")
    public Dummy postDummy(@RequestBody Dummy dummy) {
        return repository.save(dummy);
    }

    @GetMapping("/dummy")
    public List<Dummy> getDummy() {
        return repository.findAll();
    }
}
