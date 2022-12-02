package jambo.dto;

import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudyBoardDTO {

    private User user;

    private String title;

    private String content;

    private List<String> imgFiles;

    private int views;

    private boolean isReported;

    private int numberOfRecruits;

    private String period;

    private List<String> techStacks = new ArrayList<>();

    private String supportMethod;

    private boolean isOnline;

    private boolean isRecruiting;

    private String category;

    private String startDate;

    public StudyBoardDTO(User user, String title, String content, List<String> imgFiles, int numberOfRecruits, String period, String supportMethod, boolean isOnline, boolean isRecruiting, String startDate , String category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.imgFiles = imgFiles;
        this.numberOfRecruits = numberOfRecruits;
        this.period = period;
        this.supportMethod = supportMethod;
        this.isOnline = isOnline;
        this.isRecruiting = isRecruiting;
        this.startDate = startDate;
        this.category = category;
    }

    public StudyBoard toEntity() {
        return new StudyBoard(user, title, content
                                , imgFiles, category, numberOfRecruits
                                , period, supportMethod ,isOnline, isRecruiting, startDate);
    }
}
