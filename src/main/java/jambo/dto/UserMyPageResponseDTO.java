package jambo.dto;

import jambo.domain.user.User;
import jambo.domain.user.UserTechStack;
import jambo.domain.user.type.MBTI;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserMyPageResponseDTO {

    private String mainEmotion;
    private String nickName;
    private String name;
    private String email;
    private String phone;
    private MBTI mbti;
    private List<UserTechStack> userTechStacks = new ArrayList<>();
    private Integer point;
    private Integer level;
    private LocalDateTime joinDate;

    public static UserMyPageResponseDTO from(User user) {
        int level = user.getPoint().getTotalPoint() / 5;
        System.out.println();
//        int level = 5;

        return new UserMyPageResponseDTO(
                user.getMainEmotion(),
                user.getNickName(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getMbti(),
                user.getUserTechStacks(),
                user.getPoint().getAvailablePoint(),
                level,
                user.getJoinDate()
        );
    }
}
