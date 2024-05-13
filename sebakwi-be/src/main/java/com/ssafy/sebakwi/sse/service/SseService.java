package com.ssafy.sebakwi.sse.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.sebakwi.sse.domain.EmitterRepository;
import com.ssafy.sebakwi.sse.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SseService {

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
                sendErrorMessage(emitter, e, uuid);
            }
        }
    }

    private SseEmitter createEmitter(UUID uuid) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(uuid, emitter);

        emitter.onCompletion(() -> emitterRepository.deleteById(uuid));
        emitter.onTimeout(() -> emitterRepository.deleteById(uuid));
        emitter.onError((ex) -> {
            emitterRepository.deleteById(uuid);
            log.error("SSE error={}", ex.getMessage());
        });

        return emitter;
    }

    private void sendErrorMessage(SseEmitter emitter, Exception ex, UUID uuid) {
        try {
            emitter.send(SseEmitter.event()
                    .id(String.valueOf(uuid))
                    .name("error")
                    .data(objectMapper.writeValueAsString(new ErrorResponse(ex.getMessage()))));
        } catch (IOException e) {
            log.info("Error occured while sending error message={}", e.getMessage());
        } finally {
            emitter.completeWithError(ex);
        }
    }
}
