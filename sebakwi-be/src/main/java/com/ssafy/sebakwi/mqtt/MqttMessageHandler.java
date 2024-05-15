package com.ssafy.sebakwi.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.sebakwi.checkupList.controller.CheckupListController;
import com.ssafy.sebakwi.checkupList.service.CheckupListService;
import com.ssafy.sebakwi.mqtt.dto.CheckupData;
import com.ssafy.sebakwi.util.SimpleRestClient;
import com.ssafy.sebakwi.wheel.dto.CreateWheelRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;




@Service
@Slf4j
@RequiredArgsConstructor
public class MqttMessageHandler {

    final CheckupListController checkupListController;
    final CheckupListService checkupListService;
    final SimpleRestClient simpleRestClient;
    private final ObjectMapper objectMapper;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<?> message) {
        String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
        String payload = (String) message.getPayload();

        try {
            CreateWheelRequest checkupData = objectMapper.readValue(payload, CreateWheelRequest.class);
            log.info("checkupData to json-> {}: {}", topic, checkupData);

            // 전송관련 이슈로 simpleRestClient사용
//             checkupListController.saveWheel(checkupData);
             simpleRestClient.sendPostRequest(checkupData);

        } catch (Exception e) {
            log.error("Error handling MQTT message: {}", e.getMessage(), e);
        }

    }
}
