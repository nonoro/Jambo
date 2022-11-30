package jambo.mvc.domain.board;

import jambo.mvc.domain.board.type.Category;
import jambo.mvc.domain.user.User;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

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

    @CreatedDate
    private LocalDateTime writeDate;

    @OneToMany(mappedBy = "board")
    private List<ImgFile> imgFiles;

    private int views;

    private boolean isReported;

    private Category category;
}
