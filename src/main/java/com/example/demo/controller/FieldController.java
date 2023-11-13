package com.example.demo.controller;

import com.example.demo.entity.FieldDTO;
import com.example.demo.entity.MeasuredData;
import com.example.demo.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class FieldController {
    @Autowired
    private FieldService fieldService;
//    @PostMapping("/insertField")
//    public String insertField(@RequestBody FielDTO field1) throws ExecutionException, InterruptedException {
//        return fieldService.insertField(field1);
//    }

    @PostMapping("/insertField")
    public String insertField(@RequestBody String field1) {
        return fieldService.insertMyField(field1);
    }

    @PostMapping("/getListField")
    public String getListField() {
        CompletableFuture<String> future = fieldService.getListFieldNew();
        return future.join();
    }
    @PostMapping("/updateWeatherData")
    public void updateWeatherData() {
        try {
            fieldService.updateWeatherData("Field1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/updateHistory")
    public String updateHistory(@RequestBody String input) {
        return fieldService.updateHistory(input);

    }
//    @PostMapping("/getWeatherData")
//    public String getWeatherData(@RequestBody String input) {
//        CompletableFuture<String> future = fieldService.getWeatherData(input);
//        return future.join();
//    }

    // test trường hợp dùng repository
    @PostMapping("/getModelField")
    public String getModelField() throws IOException {
        return fieldService.getModelField();
    }
    @PostMapping("/calculateModel")
    public CompletableFuture<String> caculateModel(@RequestBody String fieldName) {
        return fieldService.calculateModel(fieldName);
    }
    @PostMapping("/getWeatherData")
    public CompletableFuture<List<MeasuredData>> getWeatherData(@RequestBody String fieldName) {
        return fieldService.getWeatherData(fieldName);
    }
    @PostMapping("/deleteField")
    public void deleteField(@RequestBody String fieldName) {
        fieldService.deleteField(fieldName);
    }
    @PostMapping("/updateCustomizedParameters")
    public String updateCustomizedParameters(@RequestBody FieldDTO input) {
        return fieldService.updateCustomizedParameters(input);
    }

    @PostMapping("/setIrrigation")
    public String setIrrigation(@RequestBody String input) {
        return fieldService.setIrrigation(input);
    }

}
