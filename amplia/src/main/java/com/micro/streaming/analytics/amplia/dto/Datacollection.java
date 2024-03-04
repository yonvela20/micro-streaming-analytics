package com.micro.streaming.analytics.amplia.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Datacollection {
    private String version;
    private ArrayList<Datastreams> datastreams;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<Datastreams> getDatastreams() {
        return datastreams;
    }

    public void setDatastreams(ArrayList<Datastreams> datastreams) {
        this.datastreams = datastreams;
    }
}
