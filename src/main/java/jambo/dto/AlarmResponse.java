package jambo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlarmResponse {

    private String title;
    private String body;

    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime createdAt;


    public static AlarmResponse comment(String body) {
        return new AlarmResponse("댓글", body);
    }

    public static AlarmResponse note(String body) {
        return new AlarmResponse("쪽지", body);
    }

    private AlarmResponse(String title, String body) {
        this.title = title;
        this.body = body;
        this.createdAt = LocalDateTime.now();
    }
}
