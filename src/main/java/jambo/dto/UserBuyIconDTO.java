package jambo.dto;

import jambo.domain.user.Icon;
import jambo.domain.user.Point;
import jambo.domain.user.UserTechStack;
import jambo.domain.user.type.MBTI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBuyIconDTO {
    private String email;

    private String iconName;
}
