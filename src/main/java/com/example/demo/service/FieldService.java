package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repositories.FieldRepository;
import com.google.firebase.database.*;
import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@EnableAsync
public class FieldService {
    private static final String COLLECTION_NAME = "fields";
    private static FieldRepository fieldRepository;
    private DatabaseReference databaseReference;

    @Autowired
    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    // oki
    public String insertField(FieldDTO fieldDTO) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("user");
        final String[] result = {""};
        FieldDTO fieldDTO1 = new FieldDTO(fieldDTO);
        ref.child(fieldDTO.getFieldName()).setValue(fieldDTO1, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    // Xử lý lỗi nếu có
                    result[0] = "Data could not be saved. " + databaseError.getMessage();
                } else {
                    // Ghi dữ liệu thành công
                    result[0] = "Data saved successfully.";
                }
            }
        });
        return result[0];
    }

    // oki
    public static CompletableFuture<String> getListField() {
        CompletableFuture<String> future = new CompletableFuture<>();
        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("user");
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FieldDTO> fieldList = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    FieldDTO fieldDTO = mapField(child);
                    fieldList.add(fieldDTO);
                }
                Gson gson = new Gson();
                String json = gson.toJson(fieldList);
                future.complete(json);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;
    }

    public static CompletableFuture<FieldDTO> getField(String nameField) {
        CompletableFuture<FieldDTO> future = new CompletableFuture<>();
        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("user/" + nameField);
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FieldDTO fieldDTO = mapField(dataSnapshot);
                future.complete(fieldDTO);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;
    }

    public static FieldDTO mapField(DataSnapshot dataSnapshot) {
        try {
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldName(dataSnapshot.getKey());
            fieldDTO.setdAP(dataSnapshot.child("dAP").getValue(Integer.class));
            fieldDTO.setStartTime(dataSnapshot.child("startTime").getValue(String.class));
            fieldDTO.setCustomizedParameters(dataSnapshot.child("customizedParameters").getValue(CustomizedParameters.class));
            fieldDTO.setStartIrrigation(dataSnapshot.child("startIrrigation").getValue(String.class));
            fieldDTO.setIrrigationCheck(dataSnapshot.child("irrigationCheck").getValue(Boolean.class));
            fieldDTO.setIrrigationInformation(dataSnapshot.child("irrigationInformation").getValue(IrrigationInformation.class));
            fieldDTO.setHistoryIrrigation(dataSnapshot.child("historyIrrigation").getValue(HistoryIrrigation.class));
            return fieldDTO;
        } catch (Exception e) {
            return null;
        }
    }

    //oki
    public String updateHistory(String input) {
        try {
            JSONObject jsonObject = new JSONObject(input);
            String fieldName = jsonObject.getString("fieldName");
            String userName = jsonObject.getString("userName");
            Double amount = jsonObject.getDouble("amount");
            String time = jsonObject.getString("time");
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            HistoryIrrigation historyIrrigation = new HistoryIrrigation(time, userName, amount);
            // Sử dụng đối tượng DatabaseReference để cập nhật dữ liệu
            databaseReference.child("user/" + fieldName + "/historyIrrigation").setValue(historyIrrigation, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        System.out.println("Data could not be saved: " + databaseError.getMessage());
                    } else {
                        System.out.println("Data saved successfully.");
                    }
                }
            });
            return "OK";
        } catch (Exception e) {
            return e.toString();
        }
    }

    public static CompletableFuture<List<MeasuredData>> getWeatherData(String input) {
        CompletableFuture<List<MeasuredData>> future = new CompletableFuture<>();
        DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("user/" + input + "/measuredData");
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<MeasuredData> measuredDataList = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    for (DataSnapshot child1 : child.getChildren()) {
                        MeasuredData measuredData = child1.getValue(MeasuredData.class);
                        measuredDataList.add(measuredData);
                    }
                }
                future.complete(measuredDataList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;
    }
//    public static List<MeasuredData> readCSVFile(String filePath) throws IOException {
//        List<MeasuredData> measuredDataList = new ArrayList<>();
//
//        try (FileReader fileReader = new FileReader(filePath);
//             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT)) {
//            int i = 0;
//            for (CSVRecord csvRecord : csvParser) {
//                if (i == 0) {
//                    i++;
//                    continue;
//                }
//                MeasuredData measuredData = new MeasuredData();
//                measuredData.setRainFall(Double.parseDouble(csvRecord.get(3)));
//                measuredData.setRelativeHumidity(Double.parseDouble(csvRecord.get(4)));
//                measuredData.setTemperature(Double.parseDouble(csvRecord.get(5)));
//                measuredData.setWindSpeed(Double.parseDouble(csvRecord.get(6)));
//                measuredData.setRadiation(Double.parseDouble(csvRecord.get(2)));
//                measuredData.setTime(csvRecord.get(0));
//
//                measuredDataList.add(measuredData);
//            }
//        }
//        return measuredDataList;
//    }

//    public static void upDateWeatherData() {
//        String filePath = "C:\\Users\\Admin\\Downloads\\weather_data.xlsx"; // Đặt đường dẫn đến tệp CSV của bạn
//        try {
//            List<MeasuredData> measuredDataList = readCSVFile(filePath);
//            System.out.println(measuredDataList.size());
//            // Bây giờ bạn có thể làm gì đó với danh sách measuredDataList
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void updateWeatherData(String name) throws IOException {
        List<List<Object>> weatherData = new ArrayList<>();
        String path = "H:\\demo1\\weatherDataVietNam.csv";
        File csvFile = new File(path);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        FileReader fileReader = new FileReader(csvFile);
        CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
        // Định dạng cho ngày và giờ
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        for (CSVRecord record : csvParser) {
            List<Object> rowData = new ArrayList<>();
            for (String value : record) {
                rowData.add(value);
            }
            weatherData.add(rowData);
        }
        int i = 0;
        try{
            List<MeasuredData> measuredDataList = new ArrayList<>();
            for (i = 3; i < weatherData.size(); i++) {
                MeasuredData measuredData = new MeasuredData(name);
                measuredData.setTime(weatherData.get(i).get(0).toString());
                measuredData.setRadiation(Float.parseFloat(weatherData.get(i).get(2).toString()));
                measuredData.setRainFall(Double.parseDouble(weatherData.get(i).get(3).toString()));
                measuredData.setRelativeHumidity(Double.parseDouble(weatherData.get(i).get(4).toString()));
                measuredData.setWindSpeed(Double.parseDouble(weatherData.get(i).get(6).toString()));
                measuredData.setTemperature(Double.parseDouble(weatherData.get(i).get(5).toString()));
                measuredDataList.add(measuredData);
                try {
                    // Chuyển đổi chuỗi thời gian thành đối tượng Date
                    Date date = dateFormat.parse(measuredData.getTime());

                    // Tách ngày và giờ từ đối tượng Date
                    SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat timeOnlyFormat = new SimpleDateFormat("HH:mm:ss");


                    String datePart = dateOnlyFormat.format(date);
                    String timePart = timeOnlyFormat.format(date);
                    measuredData.setTime(datePart + " " + timePart);
                    DatabaseReference ref = database.getReference("user");

                    ref.child(name + "/measuredData/" + datePart + "/" + timePart).setValueAsync(measuredData);
                    if (i == 1) {
                        ref.child(name + "/startTime").setValueAsync(measuredData.getTime());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e ) {
            System.out.println(weatherData.get(i).get(0));
        }
        csvParser.close();
        fileReader.close();
    }


    // code new
    public static CompletableFuture<DataSnapshot> fetchData1(String path) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dataRef = databaseReference.child(path);
        CompletableFuture<DataSnapshot> future = new CompletableFuture<>();

        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                future.complete(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;
    }

    // Thêm test thử trường hợp dùng repository
    public static CompletableFuture<String> getListFieldNew() {
        CompletableFuture<String> lstField = fieldRepository.getListField();
        return lstField;
    }

    public String getModelField() throws IOException {
        return fieldRepository.getModelField();
    }

    @Async
    @EventListener
    public CompletableFuture<String> calculateModel(String nameField) {
        CompletableFuture<List<MeasuredData>> measureDataListFuture = getWeatherData(nameField);
        CompletableFuture<FieldDTO> fieldDTOFuture = getField(nameField);

        return CompletableFuture.allOf(measureDataListFuture, fieldDTOFuture)
                .thenApply(ignored -> {
                    List<MeasuredData> measuredDataList = measureDataListFuture.join();
                    Field field = new Field("nameField");
                    List<List<Object>> weatherData = new ArrayList<>();

                    for (MeasuredData measuredData : measuredDataList) {
                        String time = measuredData.getTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date;
                        try {
                            date = dateFormat.parse(time);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                        double doy = field.getDoy(date);
                        double radiation = measuredData.getRadiation();
                        double rain = measuredData.getRainFall();
                        double relative = measuredData.getRelativeHumidity();
                        double temperature = measuredData.getTemperature();
                        double wind = measuredData.getWindSpeed();

                        List<Object> result = new ArrayList<>();
                        result.add(time);
                        result.add(doy);
                        result.add(radiation);
                        result.add(rain);
                        result.add(relative);
                        result.add(temperature);
                        result.add(wind);

                        weatherData.add(result);
                    }

                    field._weatherData.add(weatherData.get(0));
                    field._weatherData.add(weatherData.get(1));
                    for (int i = 1; i < weatherData.size() - 2; i++) {
                        if (Double.parseDouble( weatherData.get(i + 1).get(1).toString()) - Double.parseDouble(field._weatherData.get(field._weatherData.size() - 1).get(1).toString()) >= 0.01) {
                            field._weatherData.add(weatherData.get(i));
                        }
                    }
                    field.simulate();

                    Gson gson = new Gson();
                    String json = gson.toJson(field._results);
                    return json;
                });
    }

    //Xóa một cánh đồng
    public void deleteField(String field) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("user");
        database.child(field).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error == null) {
                    System.out.println("Field deleted successfully");
                } else {
                    System.out.println("Error deleting field: " + error.getMessage());
                }
            }
        });
    }

    // oki
    public String insertMyField(String input) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("user");
        JSONObject jsonData = new JSONObject(input);
        String nameField = jsonData.optString("fieldName");
        FieldDTO fieldDTO= new FieldDTO(nameField);
        final String[] result = {""};
        ref.child(nameField).setValue(fieldDTO, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    // Xử lý lỗi nếu có
                    result[0] = "Data could not be saved. " + databaseError.getMessage();
                } else {
                    // Ghi dữ liệu thành công
                    result[0] = "Data saved successfully.";
                }
            }
        });
        return result[0];
    }
}
