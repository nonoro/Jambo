package jambo.domain.board;

import jambo.domain.TechStack;
import jambo.domain.board.type.SupportMethod;
import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyBoard extends Board {
    private int numberOfRecruits;
    private String period;

    @OneToMany(mappedBy = "studyBoard", cascade = CascadeType.ALL)
    private List<StudyBoardTechStack> studyBoardTechStacks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private SupportMethod supportMethod;

    private boolean isOnline;

    private boolean isRecruiting = true;

    private String startDate;

    public StudyBoard(User user, String title, String content, String category, int numberOfRecruits, String period, String supportMethod ,boolean isOnline, String startDate) {
        super(user, title, content, category);
        this.numberOfRecruits = numberOfRecruits;
        this.period = period;
        this.supportMethod = SupportMethod.mapping(supportMethod);
        this.isOnline = isOnline;
        this.startDate = startDate;
    }


    public void setTechStacks(List<TechStack> techStacks) {
        List<StudyBoardTechStack> newStudyBoardTechStacks = StudyBoardTechStack.of(techStacks);
        this.studyBoardTechStacks = newStudyBoardTechStacks;

        newStudyBoardTechStacks.forEach(studyBoardTechStack -> studyBoardTechStack.setStudyBoard(this));
    }
}
