package com.example.demo.entity;

public class Humidity {
    String humidity30;
    String humidity60;
    String time;

    public String getHumidity30() {
        return humidity30;
    }

    public Humidity() {
    }

    public Humidity(String humidity30, String humidity60, String time) {
        this.humidity30 = humidity30;
        this.humidity60 = humidity60;
        this.time = time;
    }

    public void setHumidity30(String humidity30) {
        this.humidity30 = humidity30;
    }

    public String getHumidity60() {
        return humidity60;
    }

    public void setHumidity60(String humidity60) {
        this.humidity60 = humidity60;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
