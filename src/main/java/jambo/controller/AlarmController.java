package jambo.controller;

import jambo.domain.user.User;
import jambo.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping("/alarm/subscribe")
    public SseEmitter subscribe(@RequestParam Long userId) {
        return alarmService.connectAlarm(userId);
    }
}
