package jambo.dto;

import jambo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String mainEmotion;

    private String nickName;

    private Integer point;

    private Integer level;

    public static UserResponseDTO from(User user) {
        int level = user.getPoint().getTotalPoint() / 123;

        return new UserResponseDTO(user.getMainEmotion(), user.getNickName(), user.getAvailablePoint(), level);
    }
}
