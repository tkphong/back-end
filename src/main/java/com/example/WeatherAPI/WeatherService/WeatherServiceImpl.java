package com.example.WeatherAPI.WeatherService;

import com.example.WeatherAPI.WeatherRepository.FakeRepository;
import com.example.WeatherAPI.WeatherRepository.WeatherRepository;
import com.example.WeatherAPI.entity.FakeRow;
import com.example.WeatherAPI.entity.WeatherColumn;
import com.example.WeatherAPI.model.FakeRecord;
import com.example.WeatherAPI.model.WeatherRecord;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import javax.annotation.PostConstruct;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final FakeRepository fakeRepository;
    private List<FakeRecord> diagramRecords;
    private List<WeatherRecord> listRecords;

    @Value("${mqtt.broker.url}")
    private String mqttBrokerUrl;

    @Value("${mqtt.client.id}")
    private String mqttClientId;

    @Value("${mqtt.topic.temperature}")
    private String mqttTopicTemperature;

    @Value("${mqtt.topic.humidity}")
    private String mqttTopicHumidity;

    public WeatherServiceImpl(WeatherRepository weatherRepository, FakeRepository fakeRepository) {
        this.weatherRepository = weatherRepository;
        this.fakeRepository = fakeRepository;
        this.diagramRecords = new ArrayList<>();
        this.listRecords = new ArrayList<>();
    }

    @Override
    public WeatherRecord getLatestRecord() {
        WeatherColumn weatherColumn = weatherRepository.findTopByOrderByIdDesc();
        WeatherRecord weatherRecord = new WeatherRecord();
        BeanUtils.copyProperties(weatherColumn, weatherRecord);
        weatherRecord.setTimeString(String.valueOf(weatherColumn.getDateTime()));
        return weatherRecord;
    }

    @Override
    public List<WeatherRecord> getListRecords() {
        List<WeatherColumn> weatherColumnList = weatherRepository.findAll();
        List<WeatherRecord> weatherRecordList = weatherColumnList
                .stream().map(column -> {
                    WeatherRecord weatherRecord = new WeatherRecord();
                    BeanUtils.copyProperties(column, weatherRecord);
                    weatherRecord.setTimeString(String.valueOf(column.getDateTime()));
                    return weatherRecord;
                }).collect(Collectors.toList());
        this.listRecords = weatherRecordList;
        return weatherRecordList;
    }

    @Override
    public List<FakeRecord> getDiagramRecords() {
        List<FakeRow> fakeRowList = fakeRepository.findTop30ByOrderByIdDesc();
        List<FakeRecord> diagramRecords = fakeRowList
                .stream().map(row -> new FakeRecord(
                        row.getTemperature(),
                        row.getHumidity()
                )).collect(Collectors.toList());
        return diagramRecords;
    }

    @Override
    public WeatherColumn addLatest() {
        // Tạo callback để xử lý khi nhận được tin nhắn từ MQTT broker
        MqttCallback callback = new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                // Xử lý khi kết nối bị mất
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                // Xử lý khi nhận được tin nhắn từ MQTT broker
                if (topic.equals(mqttTopicTemperature)) {
                    // Xử lý dữ liệu nhiệt độ
                    int temperature = Integer.parseInt(new String(message.getPayload()));
                    // Lưu dữ liệu nhiệt độ vào bảng fake_records
                    FakeRow fakeRow = new FakeRow();
                    fakeRow.setTemperature(temperature);
                    fakeRepository.save(fakeRow);
                } else if (topic.equals(mqttTopicHumidity)) {
                    // Xử lý dữ liệu độ ẩm
                    int humidity = Integer.parseInt(new String(message.getPayload()));
                    // Lưu dữ liệu độ ẩm vào bảng weather_records
                    WeatherColumn weatherColumn = new WeatherColumn();
                    weatherColumn.setHumidity(humidity);
                    weatherRepository.save(weatherColumn);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // Xử lý khi gửi tin nhắn thành công
            }
        };

        try {
            // Tạo client MQTT và cấu hình
            MqttClient mqttClient = new MqttClient(mqttBrokerUrl, mqttClientId);
            mqttClient.setCallback(callback);
            mqttClient.connect();

            // Subcribe các topic cần lắng nghe
            mqttClient.subscribe(mqttTopicTemperature);
            mqttClient.subscribe(mqttTopicHumidity);
        } catch (MqttException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to connect to MQTT broker.");
        }
        return null; // Trả về null vì dữ liệu sẽ được xử lý trong callback
    }
}
