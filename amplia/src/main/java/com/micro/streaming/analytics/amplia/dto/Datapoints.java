package com.micro.streaming.analytics.amplia.dto;

import lombok.Data;

@Data
public class Datapoints {
    private Number at;
    private Integer value;

    public Number getAt() {
        return at;
    }

    public void setAt(Number at) {
        this.at = at;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
