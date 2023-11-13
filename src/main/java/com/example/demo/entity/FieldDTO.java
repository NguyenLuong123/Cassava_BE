package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    CustomizedParameters customized_parameters;
    String measured_data;
    String startIrrigation;

    String endIrrigation;
    double _autoIrrigateTime = -1;
    IrrigationInformation irrigation_information;
    HistoryIrrigation historyIrrigation;


    public IrrigationInformation getIrrigationInformation() {
        return irrigation_information;
    }

    public void setIrrigationInformation(IrrigationInformation irrigation_information) {
        this.irrigation_information = irrigation_information;
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
        return customized_parameters;
    }

    public void setCustomizedParameters(CustomizedParameters customized_parameters) {
        this.customized_parameters = customized_parameters;
    }

    public String getMeasuredData() {
        return measured_data;
    }

    public void setMeasuredData(String measured_data) {
        this.measured_data = measured_data;
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
            CustomizedParameters customized_parameters,
            String measured_data,
            String startIrrigation,
            String endIrrigation) {
        this.fieldName = fieldName;
        this.startTime = startTime;
        this.dAP = dAP;
        this.irrigationCheck = irrigationCheck;
        this.amountOfIrrigation = amountOfIrrigation;
        this.yields = yields;
        this.checkYieldDate = checkYieldDate;
        this.customized_parameters = customized_parameters;
        this.measured_data = measured_data;
        this.startIrrigation = startIrrigation;
        this.endIrrigation = endIrrigation;
    }
    public FieldDTO(String name) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        this.startTime = currentDateTime.format(formatter);
        this.dAP = 0;
        this.irrigationCheck = false;
        this.amountOfIrrigation = 0;
        this.measured_data = "";
        this.checkYieldDate = "";
        this.customized_parameters = new CustomizedParameters(name);
        this.startIrrigation = "";
        this.endIrrigation = "";
    }

    public FieldDTO(FieldDTO fieldDTO){
        this.startTime = fieldDTO.startTime;
        this.dAP = fieldDTO.dAP;
        this.irrigationCheck = fieldDTO.irrigationCheck;
        this.amountOfIrrigation = fieldDTO.amountOfIrrigation;
        this.measured_data = "";
        this.checkYieldDate = "";
        this.customized_parameters = new CustomizedParameters(fieldDTO.customized_parameters);
        this.startIrrigation = "";
        this.endIrrigation = "";
    }

}
