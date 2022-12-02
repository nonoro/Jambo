package jambo.domain;

import jambo.domain.board.StudyBoardTechStack;
import jambo.domain.user.UserTechStack;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TechStack {
    @Id
    @Column(name = "tech_stack_id")
    @GeneratedValue
    private Long id;

    private String techStackName;

    @ToString.Exclude
    @OneToMany(mappedBy = "techStack")
    private List<UserTechStack> userTechStacks;

    @ToString.Exclude
    @OneToMany(mappedBy = "techStack")
    private List<StudyBoardTechStack> studyBoardTechStacks;

    public TechStack(String techStackName) {
        this.techStackName = techStackName;
    }
}
