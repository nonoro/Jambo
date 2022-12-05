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

    private String isOnline;

    private String category;

    private String startDate;

    public StudyBoardDTO(User user, String title, String content, int numberOfRecruits, String period, String supportMethod, String isOnline, String startDate , String category) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.numberOfRecruits = numberOfRecruits;
        this.period = period;
        this.supportMethod = supportMethod;
        this.isOnline = isOnline;
        this.startDate = startDate;
        this.category = category;
    }

    public Boolean mapping(String isOnline) {
        if (isOnline.equals("온라인")) {
            return true;
        }
            return false;
    }

    public StudyBoard toEntity() {
        return new StudyBoard(user, title, content
                                , category, numberOfRecruits
                                , period, supportMethod ,mapping(isOnline), startDate);
    }
}
