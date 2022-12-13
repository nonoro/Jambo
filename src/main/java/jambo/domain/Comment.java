package jambo.domain;

import jambo.domain.board.Board;
import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "board_comment")
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //writer

    @Column(nullable=false, length = 500)
    private String content;

    @CreationTimestamp
    private LocalDateTime regDate;

    public void save(Board board, User user) {
        this.board = board;
        this.user = user;
    }

    public Comment(Board board, User user, String content) {
        this.board = board;
        this.user = user;
        this.content = content;
    }

    //    @CreationTimestamp
//    private LocalDateTime regDate;

//    private boolean isReported;
}
