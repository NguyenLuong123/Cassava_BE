package com.example.demo.entity;

import java.util.Date;
import java.util.List;

public class FieldDTO {
    String fieldName;
    int dAP;
    String startTime;
    boolean irrigationCheck;
    double amountOfIrrigation;

    List<Double> yields;
    String checkYieldDate;
    CustomizedParameters customizedParameters;
    MeasuredData measuredData;
    String startIrrigation;

    String endIrrigation;
    double _autoIrrigateTime = -1;
    IrrigationInformation irrigationInformation;
    HistoryIrrigation historyIrrigation;


    public IrrigationInformation getIrrigationInformation() {
        return irrigationInformation;
    }

    public void setIrrigationInformation(IrrigationInformation irrigationInformation) {
        this.irrigationInformation = irrigationInformation;
    }

    public HistoryIrrigation getHistoryIrrigation() {
        return historyIrrigation;
    }

    public void setHistoryIrrigation(HistoryIrrigation historyIrrigation) {
        this.historyIrrigation = historyIrrigation;
    }

    public String getFieldName() {
        return fieldName;
    }


    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getdAP() {
        return dAP;
    }

    public void setdAP(int dAP) {
        this.dAP = dAP;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public boolean isIrrigationCheck() {
        return irrigationCheck;
    }

    public void setIrrigationCheck(boolean irrigationCheck) {
        this.irrigationCheck = irrigationCheck;
    }

    public double getAmountOfIrrigation() {
        return amountOfIrrigation;
    }

    public void setAmountOfIrrigation(double amountOfIrrigation) {
        this.amountOfIrrigation = amountOfIrrigation;
    }

    public List<Double> getYields() {
        return yields;
    }

    public void setYields(List<Double> yields) {
        this.yields = yields;
    }

    public String getCheckYieldDate() {
        return checkYieldDate;
    }

    public void setCheckYieldDate(String checkYieldDate) {
        this.checkYieldDate = checkYieldDate;
    }

    public CustomizedParameters getCustomizedParameters() {
        return customizedParameters;
    }

    public void setCustomizedParameters(CustomizedParameters customizedParameters) {
        this.customizedParameters = customizedParameters;
    }

    public MeasuredData getMeasuredData() {
        return measuredData;
    }

    public void setMeasuredData(MeasuredData measuredData) {
        this.measuredData = measuredData;
    }

    public String getStartIrrigation() {
        return startIrrigation;
    }

    public void setStartIrrigation(String startIrrigation) {
        this.startIrrigation = startIrrigation;
    }

    public String getEndIrrigation() {
        return endIrrigation;
    }

    public void setEndIrrigation(String endIrrigation) {
        this.endIrrigation = endIrrigation;
    }

    public double get_autoIrrigateTime() {
        return _autoIrrigateTime;
    }

    public void set_autoIrrigateTime(double _autoIrrigateTime) {
        this._autoIrrigateTime = _autoIrrigateTime;
    }

    public FieldDTO() {
    }

    public FieldDTO(
            String fieldName,
            String startTime,
            int dAP,
            boolean irrigationCheck,
            double amountOfIrrigation,
            List<Double> yields,
            String checkYieldDate,
            CustomizedParameters customizedParameters,
            MeasuredData measuredData,
            String startIrrigation,
            String endIrrigation) {
        this.fieldName = fieldName;
        this.startTime = startTime;
        this.dAP = dAP;
        this.irrigationCheck = irrigationCheck;
        this.amountOfIrrigation = amountOfIrrigation;
        this.yields = yields;
        this.checkYieldDate = checkYieldDate;
        this.customizedParameters = customizedParameters;
        this.measuredData = measuredData;
        this.startIrrigation = startIrrigation;
        this.endIrrigation = endIrrigation;
    }
    public FieldDTO(String name) {
        this.fieldName = name;
        this.startTime = String.valueOf(new Date(2023,10,20));
        this.dAP = 0;
        this.irrigationCheck = false;
        this.amountOfIrrigation = 0;
        this.measuredData = new MeasuredData(name);
        this.checkYieldDate = "";
        this.customizedParameters = new CustomizedParameters(name);
        this.startIrrigation = "";
        this.endIrrigation = "";
    }

    public FieldDTO(FieldDTO fieldDTO){
        this.startTime = fieldDTO.startTime;
        this.dAP = fieldDTO.dAP;
        this.irrigationCheck = fieldDTO.irrigationCheck;
        this.amountOfIrrigation = fieldDTO.amountOfIrrigation;
        this.measuredData = new MeasuredData(fieldDTO.measuredData);
        this.checkYieldDate = "";
        this.customizedParameters = new CustomizedParameters(fieldDTO.customizedParameters);
        this.startIrrigation = "";
        this.endIrrigation = "";
    }

}
