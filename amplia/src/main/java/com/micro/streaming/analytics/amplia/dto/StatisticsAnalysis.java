package com.micro.streaming.analytics.amplia.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class StatisticsAnalysis {
    @Id
    private String id;
    private ArrayList<DatastreamAnalysis> datastreamsAnalysis; // This is the id of the datacollection

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<DatastreamAnalysis> getDatastreamsAnalysis() {
        return datastreamsAnalysis;
    }

    public void setDatastreamsAnalysis(ArrayList<DatastreamAnalysis> datastreamsAnalysis) {
        this.datastreamsAnalysis = datastreamsAnalysis;
    }

    @Data
    public static class DatastreamAnalysis {
        private String datastreamId;
        private String datastreamFeed;
        private ArrayList<Integer> collectedValues;
        private double media;
        private double mediana;
        private ArrayList<Integer> moda;
        private double desviacionEstandar;
        private Map<String, Double>  cuartiles;
        private Integer maxValue;
        private Integer minValue;

        public String getDatastreamId() {
            return datastreamId;
        }

        public void setDatastreamId(String datastreamId) {
            this.datastreamId = datastreamId;
        }

        public String getDatastreamFeed() {
            return datastreamFeed;
        }

        public void setDatastreamFeed(String datastreamFeed) {
            this.datastreamFeed = datastreamFeed;
        }

        public ArrayList<Integer> getCollectedValues() {
            return collectedValues;
        }

        public void setCollectedValues(ArrayList<Integer> collectedValues) {
            this.collectedValues = collectedValues;
        }

        public double getMedia() {
            return media;
        }

        public void setMedia(double media) {
            this.media = media;
        }

        public double getMediana() {
            return mediana;
        }

        public void setMediana(double mediana) {
            this.mediana = mediana;
        }

        public ArrayList<Integer> getModa() {
            return moda;
        }

        public void setModa(ArrayList<Integer> moda) {
            this.moda = moda;
        }

        public double getDesviacionEstandar() {
            return desviacionEstandar;
        }

        public void setDesviacionEstandar(double desviacionEstandar) {
            this.desviacionEstandar = desviacionEstandar;
        }

        public Map<String, Double> getCuartiles() {
            return cuartiles;
        }

        public void setCuartiles(Map<String, Double> cuartiles) {
            this.cuartiles = cuartiles;
        }

        public Integer getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(Integer maxValue) {
            this.maxValue = maxValue;
        }

        public Integer getMinValue() {
            return minValue;
        }

        public void setMinValue(Integer minValue) {
            this.minValue = minValue;
        }
    }

}


