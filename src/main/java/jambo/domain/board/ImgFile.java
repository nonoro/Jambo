package jambo.domain.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ImgFile {
    @Id
    @Column(name = "img_file_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "img_seq")
    @SequenceGenerator(name = "img_seq", allocationSize = 1, sequenceName = "img_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String fileName;

    public ImgFile(String fileName) {
        this.fileName = fileName;
    }

    public static List<ImgFile> of(List<String> imgFile) {
        return imgFile.stream()
                .map(ImgFile::new)
                .collect(Collectors.toList());
    }
}
