package jambo.service;

import jambo.dto.AlarmResponse;
import jambo.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlarmService {

    private final static Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
    private final static String ALARM_NAME = "alarm";

    private final EmitterRepository emitterRepository;

    public void send(Long userId, AlarmResponse response) {
        emitterRepository.get(userId).ifPresentOrElse((sseEmitter) -> {
            try {
                sseEmitter.send(SseEmitter.event().name(ALARM_NAME).data(response));
            } catch (IOException e) {
                emitterRepository.delete(userId);
                throw new IllegalStateException("");
            }
        }, () -> log.info("No emitter founded"));
    }

    public SseEmitter connectAlarm(Long userId) {
        SseEmitter sseEmitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(userId, sseEmitter);

        sseEmitter.onCompletion(() -> emitterRepository.delete(userId));
        sseEmitter.onTimeout(() -> emitterRepository.delete(userId));

        try {
            sseEmitter.send(SseEmitter.event().id("").name("subscribe").data(AlarmResponse.comment("connected")));
        } catch (IOException exception) {
            throw new IllegalStateException("");
        }

        return sseEmitter;
    }
}