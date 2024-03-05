package com.micro.streaming.analytics.amplia.dto;

import lombok.Data;

@Data
public class Datapoints {
    private Number at;
    private Object value;

    public Number getAt() {
        return at;
    }

    public void setAt(Number at) {
        this.at = at;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
