package com.example.demo.entity;

public class HistoryIrrigation {
    String time;
    String userName;
    Double amount;

    public HistoryIrrigation(String time, String userName, Double amount) {
        this.time = time;
        this.userName = userName;
        this.amount = amount;
    }

    public HistoryIrrigation() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
