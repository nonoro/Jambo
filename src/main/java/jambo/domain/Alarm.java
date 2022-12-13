package jambo.domain;

import jambo.domain.user.Note;
import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Alarm {
    @Id
    @Column(name = "alarm_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alarm_seq")
    @SequenceGenerator(name = "alarm_seq", sequenceName = "alarm_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id")
    private Note note;

    private String alarmType;

    @CreatedDate
    private LocalDateTime receivedTime;

    private boolean isRead;

    public Alarm(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
        this.alarmType = "댓글";
    }

    public Alarm(User user, Note note) {
        this.user = user;
        this.note = note;
        this.alarmType = "쪽지";
    }
}
