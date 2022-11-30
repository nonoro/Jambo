package jambo.mvc.domain.board;

import jambo.mvc.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImgFile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "img_seq")
    @SequenceGenerator(name = "img_seq", allocationSize = 1, sequenceName = "img_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String fileName;

}
