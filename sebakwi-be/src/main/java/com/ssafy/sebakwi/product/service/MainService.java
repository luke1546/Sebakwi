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

@Slf4j
@RequiredArgsConstructor
@Service
public class MainService {

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final EmitterRepository emitterRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public SseEmitter subscribe(Long userId) {

        SseEmitter emitter = createEmitter(userId);
        sendToClient(userId, "안녕하세요 연결 됐습니다");

        return emitter;
    }

    public void sendMonthly(Long userId, Object event) {
        sendToClient(userId, event);
    }


    /**
     * data - 전송할 데이터
     */

    private void sendToClient(Long id, Object data) {
        SseEmitter emitter = emitterRepository.get(id);
        if (emitter != null) {
            try {
                String jsonData = objectMapper.writeValueAsString(data);
                emitter.send(SseEmitter.event().id(String.valueOf(id)).name("sse").data(jsonData));
            } catch (IOException e) {
                emitterRepository.deleteById(id);
                emitter.completeWithError(e);
            }
        }
    }

    private SseEmitter createEmitter(Long id) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(id, emitter);

        emitter.onCompletion(() -> emitterRepository.deleteById(id));
        emitter.onTimeout(() -> emitterRepository.deleteById(id));

        return emitter;
    }
}
