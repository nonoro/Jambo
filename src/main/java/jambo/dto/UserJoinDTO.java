package jambo.dto;

import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinDTO {
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String phone;
    private String mbti;

    private List<String> userTechStacks;

    public User toEntity() {
        return new User(
                email,
                password,
                name,
                nickName,
                phone,
                mbti);
    }
}
