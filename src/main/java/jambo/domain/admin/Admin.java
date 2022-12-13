package jambo.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_admin")
    @SequenceGenerator(name = "seq_admin", sequenceName = "seq_admin", allocationSize = 1)
    @Column(name = "admin_id")
    private Long id;
    // 이름, 아이디, 비밀번호, 닉네임, 가입일

    private String email;

    private String password;

    private String name;

    private String nickName;

    @CreatedDate
    private LocalDateTime joinDate;

    public Admin(String email, String password, String name, String nickName) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
    }
}
