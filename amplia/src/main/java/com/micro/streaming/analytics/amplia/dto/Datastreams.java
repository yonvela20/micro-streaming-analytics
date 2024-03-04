package com.micro.streaming.analytics.amplia.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Datastreams {
    private String id;
    private String feed;
    private ArrayList<Datapoints> datapoints;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public ArrayList<Datapoints> getDatapoints() {
        return datapoints;
    }

    public void setDatapoints(ArrayList<Datapoints> datapoints) {
        this.datapoints = datapoints;
    }
}
