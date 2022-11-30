package jambo.mvc.domain.board;

import jambo.mvc.domain.board.enumType.BoardState;
import jambo.mvc.domain.board.enumType.Category;
import jambo.mvc.domain.user.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
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

    private String content;

    private int recommendation;

    @CreationTimestamp
    private LocalDateTime writeDate;

    @OneToMany(mappedBy = "board")
    private List<ImgFile> imgFiles;

    private int views;

    private BoardState boardState;

    private Category category;





}
