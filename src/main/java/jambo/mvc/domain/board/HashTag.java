package jambo.mvc.domain.board;

import jambo.mvc.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HashTag {
    @Id
    @Column(name = "hash_tag_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hash_seq")
    @SequenceGenerator(name = "hash_seq", sequenceName = "hash_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String hashTag;
}
