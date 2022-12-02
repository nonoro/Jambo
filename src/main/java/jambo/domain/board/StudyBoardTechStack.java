package jambo.domain.board;

import jambo.domain.TechStack;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyBoardTechStack {
    @Id
    @Column(name = "board_tech_join_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_tech_join_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private StudyBoard studyBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_stack_id")
    private TechStack techStack;

    public StudyBoardTechStack(TechStack techStack) {
        this.techStack = techStack;
    }

    public static List<StudyBoardTechStack> of(List<TechStack> techStacks) {
        return techStacks.stream()
                .map(StudyBoardTechStack::new)
                .collect(Collectors.toList());
    }

    public void setUser(StudyBoard studyBoard) {
        this.studyBoard = studyBoard;
    }
}
