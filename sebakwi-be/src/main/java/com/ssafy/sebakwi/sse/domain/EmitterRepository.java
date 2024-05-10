package com.ssafy.sebakwi.sse.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class EmitterRepository {

    private final Map<UUID, SseEmitter> emitters = new ConcurrentHashMap<>();

    /**
     *
     * @param uuid
     * @param emitter - 이벤트 emitter
     */
    public void save(UUID uuid, SseEmitter emitter) {
        emitters.put(uuid, emitter);
    }

    public void deleteById(UUID uuid) {
        emitters.remove(uuid);
    }

    public SseEmitter get(UUID uuid) {
        return emitters.get(uuid);
    }

    public ArrayList<UUID> getAllUuid() {
        return new ArrayList<>(emitters.keySet());
    }

}
