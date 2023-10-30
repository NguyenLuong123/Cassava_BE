package com.example.demo.entity;

import javax.annotation.Nullable;

public class CustomizedParameters {
    @Nullable
    String fieldName;
    double fieldCapacity;
    boolean autoIrrigation;
    double irrigationDuration;
    double dripRate;
    double distanceBetweenHoles;
    double distanceBetweenRows;
    double scaleRain;
    double fertilizationLevel;
    double acreage;
    int numberOfHoles;

    @Nullable
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(@Nullable String fieldName) {
        this.fieldName = fieldName;
    }

    public CustomizedParameters() {
    }
    public CustomizedParameters(double acreage, double fieldCapacity,
                                boolean autoIrrigation, double irrigationDuration,
                                double dripRate, double distanceBetweenHoles,
                                double distanceBetweenRows, double scaleRain,
                                double fertilizationLevel, int numberOfHoles) {
        this.acreage = acreage;
        this.fieldCapacity = fieldCapacity;
        this.autoIrrigation = autoIrrigation;
        this.irrigationDuration = irrigationDuration;
        this.dripRate = dripRate;
        this.distanceBetweenHoles = distanceBetweenHoles;
        this.distanceBetweenRows = distanceBetweenRows;
        this.scaleRain = scaleRain;
        this.fertilizationLevel = fertilizationLevel;
        this.numberOfHoles = numberOfHoles;
    }


    public double getFieldCapacity() {
        return fieldCapacity;
    }

    public void setFieldCapacity(double fieldCapacity) {
        this.fieldCapacity = fieldCapacity;
    }

    public boolean isAutoIrrigation() {
        return autoIrrigation;
    }

    public void setAutoIrrigation(boolean autoIrrigation) {
        this.autoIrrigation = autoIrrigation;
    }

    public double getIrrigationDuration() {
        return irrigationDuration;
    }

    public void setIrrigationDuration(double irrigationDuration) {
        this.irrigationDuration = irrigationDuration;
    }

    public double getDripRate() {
        return dripRate;
    }

    public void setDripRate(double dripRate) {
        this.dripRate = dripRate;
    }

    public double getDistanceBetweenHoles() {
        return distanceBetweenHoles;
    }

    public void setDistanceBetweenHoles(double distanceBetweenHoles) {
        this.distanceBetweenHoles = distanceBetweenHoles;
    }

    public double getDistanceBetweenRows() {
        return distanceBetweenRows;
    }

    public void setDistanceBetweenRows(double distanceBetweenRows) {
        this.distanceBetweenRows = distanceBetweenRows;
    }

    public double getScaleRain() {
        return scaleRain;
    }

    public void setScaleRain(double scaleRain) {
        this.scaleRain = scaleRain;
    }

    public double getFertilizationLevel() {
        return fertilizationLevel;
    }

    public void setFertilizationLevel(double fertilizationLevel) {
        this.fertilizationLevel = fertilizationLevel;
    }

    public double getAcreage() {
        return acreage;
    }

    public void setAcreage(double acreage) {
        this.acreage = acreage;
    }

    public int getNumberOfHoles() {
        return numberOfHoles;
    }

    public void setNumberOfHoles(int numberOfHoles) {
        this.numberOfHoles = numberOfHoles;
    }

    public CustomizedParameters(String name) {
        this.acreage = 50;
        this.fieldCapacity = 72;
        this.distanceBetweenHoles = 30;
        this.irrigationDuration = 7;
        this.distanceBetweenRows = 100;
        this.dripRate = 1.6;
        this.fertilizationLevel = 100;
        this.scaleRain = 100;
        this.numberOfHoles = 8;
        this.autoIrrigation = true;
    }

    public CustomizedParameters(CustomizedParameters customizedParameters) {
        this.acreage = customizedParameters.acreage;
        this.fieldCapacity = customizedParameters.fieldCapacity;
        this.distanceBetweenHoles = customizedParameters.distanceBetweenHoles;
        this.irrigationDuration = customizedParameters.irrigationDuration;
        this.distanceBetweenRows = customizedParameters.distanceBetweenRows;
        this.dripRate = customizedParameters.dripRate;
        this.fertilizationLevel = customizedParameters.fertilizationLevel;
        this.scaleRain = customizedParameters.scaleRain;
        this.numberOfHoles = customizedParameters.numberOfHoles;
        this.autoIrrigation = customizedParameters.autoIrrigation;
    }

}
