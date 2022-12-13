package jambo.dto;

import jambo.domain.admin.Admin;
import jambo.domain.user.User;
import jambo.domain.user.type.MBTI;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class AdminJoinDTO {
    private String email;
    private String password;
    private String name;
    private String nickName;

    public Admin toEntity() {
        return new Admin(
                email,
                password,
                name,
                nickName
                );
    }

    public User toUser(){
        return new User(
                email,
                password,
                name,
                nickName,
                null,
                "NOTHING"
        );
    }

    public UserJoinDTO toUserJoinDTO(){
        return new UserJoinDTO(
                email,
                password,
                name,
                nickName,
                null,
                "NOTHING",
                null
        );

    }
}
