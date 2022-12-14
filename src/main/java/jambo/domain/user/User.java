package jambo.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jambo.domain.Alarm;
import jambo.domain.TechStack;
import jambo.domain.board.Recommendation;
//import jambo.domain.board.Report;
import jambo.domain.board.Report;
import jambo.domain.user.type.MBTI;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private MBTI mbti;

    @CreatedDate
    private LocalDateTime joinDate;

    private String mainEmotion = "elephant1.gif";

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserTechStack> userTechStacks = new ArrayList<>();

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Point point;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Icon> icons = new ArrayList<>();

//    @OneToMany(mappedBy = "sendUser", cascade = CascadeType.ALL)
//    private List<Note> notes;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Alarm> alarms;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recommendation> recommendation;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Report> reports;

    public User(String email, String password, String name, String nickName, String phone, String mbti) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.mbti = MBTI.mapping(mbti);
    }

    public void setTechStacks(List<TechStack> techStacks) {
        List<UserTechStack> newUserTechStacks = UserTechStack.of(techStacks);
        this.userTechStacks = newUserTechStacks;

        newUserTechStacks.forEach(userTechStack -> userTechStack.setUser(this));
    }

    public User(String email, String password, String name, String nickName, String phone, MBTI mbti, Point point) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.mbti = mbti;
        this.point = point;
    }

    public void setPoint(Point point) {
        this.point = point;
        point.setUser(this);
    }

    public void buy(IconShop iconShop) {
        Icon newIcon = new Icon(this, iconShop);

        point.use(iconShop.getPrice());
        iconShop.decreaseQty();
        icons.add(newIcon);
    }

    public int getAvailablePoint() {
        return point.getAvailablePoint();
    }

    public User addPoint(int rewardPoint) {
        point.add(rewardPoint);
        return this;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
