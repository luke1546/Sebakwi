package com.ssafy.sebakwi.sse.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.sebakwi.sse.domain.EmitterRepository;
import com.ssafy.sebakwi.util.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@ToString
public class SseService {

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final EmitterRepository emitterRepository;

    private final ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ObjectMapper objectMapper;

    public SseEmitter subscribe() {

        UUID uuid = UUID.randomUUID();
        SseEmitter emitter = createEmitter(uuid);
        sendToClient(uuid, "연결되었습니다 \uD83D\uDE80");

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
//                emitter.completeWithError(e);
                emitter.complete();

                //ErrorResponse 전송을 ExecutorService 에서 처리
                taskExecutor.execute(() -> {
                    try {
                        ErrorResponse errorResponse = ErrorResponse.builder()
                                .errorCode(9002)
                                .message(e.getMessage())
                                .errorTimestamp(LocalDateTime.now())
                                .build();
                        emitter.send(errorResponse, MediaType.parseMediaType("text/event-stream"));

                    } catch (IOException ex) {
                        log.info("sseResponseException={}", ex);
                    }
                });

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

}
