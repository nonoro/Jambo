package jambo.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class EmitterRepository {

    private final Map<Long, SseEmitter> emitterMap = new HashMap<>();

    public SseEmitter save(Long userId, SseEmitter sseEmitter) {
        emitterMap.put(userId, sseEmitter);

        return sseEmitter;
    }

    public Optional<SseEmitter> get(Long userId) {
        return Optional.ofNullable(emitterMap.get(userId));
    }

    public void delete(Long userId) {
        emitterMap.remove(userId);
    }
}