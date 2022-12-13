package jambo.dto;

import jambo.domain.user.User;
import jambo.domain.user.UserTechStack;
import jambo.domain.user.type.MBTI;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {

    private String nickName;
    private String phone;
    private MBTI mbti;
    private List<UserTechStack> userTechStacks = new ArrayList<>();

    public UserUpdateDTO toEntity(User user){
        return new UserUpdateDTO(
                user.getNickName(),
                user.getPhone(),
                user.getMbti(),
                user.getUserTechStacks()
        );
    }
}
