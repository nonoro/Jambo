package jambo.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jambo.domain.Comment;
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
import java.util.List;

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

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Recommendation> recommendation;

    @CreatedDate
    private LocalDateTime writeDate;

    private int views;

    private boolean isReported;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Report> reports;

    public Board(User user, String title, String content, String category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.category = Category.mapping(category);
    }

    public Board(User user, String title, String content, Category category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    @JsonIgnoreProperties({"board"})
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> commentList;

    public Board(Long id, User user, String title, String content, List<Recommendation> recommendation, LocalDateTime writeDate, int views, boolean isReported, Category category) {
    }
}
