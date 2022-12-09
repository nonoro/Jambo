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

    @ManyToOne(fetch = FetchType.LAZY) //이게 없으면 조인이 안된다고 함, 왜 ManyToOne인가요
    @JoinColumn(name = "user_id") //name을 왜 user_id로 줬을까 sendUser도 있는뎅
    private User sendUser;

    private String receiveUser;

    private String content;

    @CreatedDate
    private LocalDateTime sentTime;

}
