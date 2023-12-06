package com.example.demo.entity;

public class Humidity {
    Double humidity30;
    Double humidity60;
    String time;

    public Double getHumidity30() {
        return humidity30;
    }

    public Humidity() {
    }

    public Humidity(Double humidity30, Double humidity60, String time) {
        this.humidity30 = humidity30;
        this.humidity60 = humidity60;
        this.time = time;
    }

    public void setHumidity30(Double humidity30) {
        this.humidity30 = humidity30;
    }

    public Double getHumidity60() {
        return humidity60;
    }

    public void setHumidity60(Double humidity60) {
        this.humidity60 = humidity60;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
