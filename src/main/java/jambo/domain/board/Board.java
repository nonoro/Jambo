package jambo.domain.board;

import jambo.domain.board.type.Category;
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
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq")
    @SequenceGenerator(name = "board_seq", allocationSize = 1, sequenceName = "board_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    @Column(length=1000)
    private String content;

    private int recommendation;

    @CreatedDate
    private LocalDateTime writeDate;

    private int views;

    private boolean isReported;

    @Enumerated(EnumType.STRING)
    private Category category;


    public Board(User user, String title, String content, String category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.category = Category.mapping(category);
    }

}
