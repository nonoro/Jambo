package jambo.mvc.domain.board;

import jambo.mvc.domain.TechStack;
import jambo.mvc.domain.board.type.SupportMethod;

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

    private boolean isOnline;

    private boolean isRecruiting;

    private LocalDateTime startDate;
}
