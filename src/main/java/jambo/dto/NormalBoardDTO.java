package jambo.dto;


import jambo.domain.board.NormalBoard;
import jambo.domain.board.type.Category;
import jambo.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NormalBoardDTO {
    private String title;
    private String content;
    private Category category;


    public NormalBoard toEntity(User user){
        NormalBoard normalBoard = new NormalBoard(user, title, content, category);
        return normalBoard;
    }

}
