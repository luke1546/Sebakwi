package com.ssafy.sebakwi.jetson.service;

import com.ssafy.sebakwi.jetson.domain.Jetson;
import com.ssafy.sebakwi.jetson.domain.JetsonRepository;
import com.ssafy.sebakwi.jetson.dto.JetsonButtonRequest;
import com.ssafy.sebakwi.jetson.dto.JetsonButtonResponse;
import com.ssafy.sebakwi.mqtt.MqttMessageGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class JetsonService {

    private final JetsonRepository jetsonRepository;
    private final MqttMessageGateway mqttMessageGateway;

    public List<Boolean> getJetsonStatus() {
        Jetson jetson = jetsonRepository.findById(1).orElseThrow(() -> new RuntimeException("Jetson not found"));
        return List.of(jetson.isFoupstocker(), jetson.isEtching(), jetson.isCleaning(), jetson.isPhoto());
    }

    public JetsonButtonResponse clickJetsonButton(JetsonButtonRequest request) {

        Jetson jetson = jetsonRepository.findById(1).orElseThrow(() -> new RuntimeException("Jetson not found"));

        if (request.getCamera() == 0) {

            boolean newFoupstocker = !jetson.isFoupstocker();
            jetson.updateFoupstocker(newFoupstocker);
            jetsonRepository.save(jetson);
            mqttMessageGateway.sendToMqtt(Boolean.toString(newFoupstocker), "foupstocker/switch");
            log.info("newFoupstocker={}", newFoupstocker);
            return JetsonButtonResponse.builder()
                    .camera(0)
                    .power(newFoupstocker)
                    .build();

        } else if (request.getCamera() == 1) {

            boolean newEtching = !jetson.isEtching();
            jetson.updateEtching(newEtching);
            jetsonRepository.save(jetson);
            mqttMessageGateway.sendToMqtt(Boolean.toString(newEtching), "etching/switch");
            log.info("newEtching={}", newEtching);
            return JetsonButtonResponse.builder()
                    .camera(1)
                    .power(newEtching)
                    .build();

        } else if (request.getCamera() == 2) {

            boolean newCleaning = !jetson.isCleaning();
            jetson.updateCleaning(newCleaning);
            jetsonRepository.save(jetson);
            mqttMessageGateway.sendToMqtt(Boolean.toString(newCleaning), "cleaning/switch");
            log.info("newCleaning={}", newCleaning);
            return JetsonButtonResponse.builder()
                    .camera(2)
                    .power(newCleaning)
                    .build();

        } else {

            boolean newPhoto = !jetson.isPhoto();
            jetson.updatePhoto(newPhoto);
            jetsonRepository.save(jetson);
            mqttMessageGateway.sendToMqtt(Boolean.toString(newPhoto), "photo/switch");
            log.info("newPhoto={}", newPhoto);
            return JetsonButtonResponse.builder()
                    .camera(3)
                    .power(newPhoto)
                    .build();
        }
    }
}
