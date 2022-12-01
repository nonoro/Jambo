package jambo.domain.board;

import jambo.domain.board.type.Category;
import jambo.domain.board.type.SupportMethod;
import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyBoard extends Board {
    private int numberOfRecruits;
    private String period;

    @OneToMany(mappedBy = "studyBoard")
    private List<BoardTechStack> techStacks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private SupportMethod supportMethod;

    private boolean isOnline;

    private boolean isRecruiting;

    private LocalDateTime startDate;

    public StudyBoard(User user, String title, String content, int recommendation, LocalDateTime writeDate, List<ImgFile> imgFiles, int views, boolean isReported, Category category, int numberOfRecruits, String period, List<BoardTechStack> techStacks, SupportMethod supportMethod, boolean isOnline, boolean isRecruiting, LocalDateTime startDate) {
        super(user, title, content, recommendation, writeDate, imgFiles, views, isReported, category);
        this.numberOfRecruits = numberOfRecruits;
        this.period = period;
        this.techStacks = techStacks;
        this.supportMethod = supportMethod;
        this.isOnline = isOnline;
        this.isRecruiting = isRecruiting;
        this.startDate = startDate;
    }
}
