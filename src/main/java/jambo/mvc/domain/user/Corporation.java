package jambo.mvc.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Corporation {
    @Id
    @Column(name = "corporation_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "corporation_seq")
    @SequenceGenerator(name = "corporation_seq", sequenceName = "corporation_seq", allocationSize = 1)
    private Long id;

    private String password;

    private String phone;

    private String name;

    @CreatedDate
    private LocalDateTime joinDate;

    private String mainIcon;
}
