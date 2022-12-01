package jambo.dto;

import jambo.domain.board.BoardTechStack;
import jambo.domain.board.ImgFile;
import jambo.domain.board.type.Category;
import jambo.domain.board.type.SupportMethod;
import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
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

    private int recommendation;

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
}
