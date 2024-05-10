package com.ssafy.sebakwi.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.sebakwi.product.domain.EmitterRepository;
import com.ssafy.sebakwi.product.domain.WheelRepository;
import com.ssafy.sebakwi.product.dto.WheelMonthlyStatus;
import com.ssafy.sebakwi.product.dto.WheelMonthlyStatusResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class MainService {

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final EmitterRepository emitterRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public SseEmitter subscribe() {

        UUID uuid = UUID.randomUUID();
        SseEmitter emitter = createEmitter(uuid);
        sendToClient(uuid, "안녕하세요 연결 됐습니다");

        return emitter;
    }

    public void sendMonthly(UUID uuid, Object event) {
        sendToClient(uuid, event);
    }


    /**
     * data - 전송할 데이터
     */

    private void sendToClient(UUID uuid, Object data) {
        SseEmitter emitter = emitterRepository.get(uuid);
        if (emitter != null) {
            try {
                String jsonData = objectMapper.writeValueAsString(data);
                emitter.send(SseEmitter.event().id(String.valueOf(uuid)).name("sse").data(jsonData));
            } catch (IOException e) {
                emitterRepository.deleteById(uuid);
                emitter.completeWithError(e);
            }
        }
    }

    private SseEmitter createEmitter(UUID uuid) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(uuid, emitter);

        emitter.onCompletion(() -> emitterRepository.deleteById(uuid));
        emitter.onTimeout(() -> emitterRepository.deleteById(uuid));

        return emitter;
    }
}
