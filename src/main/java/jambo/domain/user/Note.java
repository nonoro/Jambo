package jambo.domain.user;

import lombok.*;
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
@ToString
public class Note {
    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_seq")
    @SequenceGenerator(name = "note_seq", sequenceName = "note_seq", allocationSize = 1)
    private Long id;

    private String sendUser;

    private String nickName;
    private String receiveUser;

    private String content;

    @CreatedDate
    private LocalDateTime sentTime;

    public Note(String sendUser, String receiveUser, String content) {
        this.sendUser = sendUser;
        this.receiveUser = receiveUser;
        this.content = content;
    }
}
