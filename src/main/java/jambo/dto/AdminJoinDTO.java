package jambo.dto;

import jambo.domain.admin.Admin;
import jambo.domain.user.User;
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
}
