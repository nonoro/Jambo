package jambo.domain.board;

import jambo.domain.board.type.Category;
import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<ImgFile> imgFiles;

    private int views;

    private boolean isReported;

    private Category category;

    public Board(User user, String title, String content, int recommendation, LocalDateTime writeDate, List<ImgFile> imgFiles, int views, boolean isReported, Category category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.recommendation = recommendation;
        this.writeDate = writeDate;
        this.imgFiles = imgFiles;
        this.views = views;
        this.isReported = isReported;
        this.category = category;
    }
}
