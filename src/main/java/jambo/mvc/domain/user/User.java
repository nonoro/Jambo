package jambo.mvc.domain.user;

import jambo.mvc.domain.*;
import jambo.mvc.domain.board.Note;
import jambo.mvc.domain.user.type.MBTI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_jambo")
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    @OneToMany(mappedBy = "user")
    private List<TechStack> techStacks = new ArrayList<>();

    @CreatedDate
    private LocalDateTime joinDate;


    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Point point;

    private String mainEmotion;

    @OneToMany(mappedBy = "user")
    private List<Icon> icons;

    @OneToMany(mappedBy = "sendUser")
    private List<Note> notes;

    @OneToMany(mappedBy = "user")
    private List<Alarm> alarms;
}
