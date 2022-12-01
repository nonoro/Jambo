package jambo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Point {
    @Id
    @Column(name = "point_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_seq")
    @SequenceGenerator(name = "point_seq", sequenceName = "point_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int totalPoint;
    private int available_point;
}
