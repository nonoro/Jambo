package jambo.mvc.domain.board;

import jambo.mvc.domain.TechStack;
import jambo.mvc.domain.board.enumType.StaffState;
import jambo.mvc.domain.board.enumType.StudyLocation;
import jambo.mvc.domain.board.enumType.SupportMethod;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StudyBoard extends Board {
    private int numberOfRecruits;
    private String period;

    @OneToMany(mappedBy = "board")
    private List<TechStack> techStacks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private SupportMethod supportMethod;
    @Enumerated(EnumType.STRING)
    private StudyLocation studyLocation;
    @Enumerated(EnumType.STRING)
    private StaffState staffState;

    private LocalDateTime startDate;
}
