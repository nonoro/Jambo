package jambo.domain;

import jambo.domain.user.User;
import jambo.dto.UserJoinDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecommendationTest {

    @Test
    void recommendationTest() {
        User userA = new UserJoinDTO("kkk@naver.com", "1234",
                "규정", "규정업", "010-3349-4187",
                "ENFP", List.of()).toEntity();


//        userRepository.save(userA);
//        userRepository.save(userB);

//        List<User> findUser = userRepository.findByMbti(MBTI.ENFP);

//        assertThat(findUser).contains(userA);
    }
}
