package jambo.domain.board;

import jambo.domain.TechStack;
import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardTechStack {
    @Id
    @Column(name = "board_tech_join_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private StudyBoard studyBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_stack_id")
    private TechStack techStack;

    public BoardTechStack(TechStack techStack) {
        this.techStack = techStack;
    }

    public static List<BoardTechStack> of(List<TechStack> techStacks) {
        return techStacks.stream()
                .map(BoardTechStack::new)
                .collect(Collectors.toList());
    }

    public void setUser(StudyBoard studyBoard) {
        this.studyBoard = studyBoard;
    }
}
