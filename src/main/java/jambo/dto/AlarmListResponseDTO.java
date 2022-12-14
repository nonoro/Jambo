package jambo.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AlarmListResponseDTO {

    private Long userId;

    private Long boardId;

    private Long noteId;

    private String sendUser;

    private String alarmType;

    private LocalDateTime receivedTime;

    private boolean isRead;

    public AlarmListResponseDTO(Long userId, Long boardId, LocalDateTime receivedTime) {
        this.userId = userId;
        this.boardId = boardId;
        this.alarmType = "댓글";
        this.receivedTime = receivedTime;
    }

    public AlarmListResponseDTO(Long userId, Long noteId, String sendUser, LocalDateTime receivedTime) {
        this.userId = userId;
        this.noteId = noteId;
        this.sendUser = sendUser;
        this.alarmType = "쪽지";
        this.receivedTime = receivedTime;
    }
}
