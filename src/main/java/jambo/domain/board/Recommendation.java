package jambo.domain.board;

import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id
    @Column(name = "recommend_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recommendation_seq")
    @SequenceGenerator(name = "recommendation_seq", sequenceName = "recommendation_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public Recommendation(User user, Board board) {
        this.user = user;
        this.board = board;
    }
}
