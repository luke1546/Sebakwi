package com.ssafy.sebakwi.product.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class EmitterRepository {

    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    /**
     *
     * @param id
     * @param emitter - 이벤트 emitter
     */
    public void save(Long id, SseEmitter emitter) {
        emitters.put(id, emitter);
    }

    public void deleteById(Long id) {
        emitters.remove(id);
    }

    public SseEmitter get(Long id) {
        return emitters.get(id);
    }

}
