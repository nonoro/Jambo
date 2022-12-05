package jambo.domain.user;

import jambo.domain.TechStack;
import jambo.domain.user.type.MBTI;
import jambo.dto.UserJoinDTO;
import jambo.repository.TechStackRepository;
import jambo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Commit
public class UserTechStackTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechStackRepository techStackRepository;

    @Test
    public void findStackTest() {
        techStackRepository.saveAll(getTechStackFixtures("C", "Java", "Python"));

        List<TechStack> findTechStacks = techStackRepository.findAllByTechStackNameIn(List.of("Java", "Python"));
        assertThat(findTechStacks).hasSize(2);
    }

    @Test
    public void saveUserTest() {
        techStackRepository.saveAll(getTechStackFixtures("C", "Java", "Python"));

        List<String> userRequestTechStacks = List.of("C", "Java", "Python");
        UserJoinDTO userJoinDto1 = new UserJoinDTO("kkk@naver.com", "1234",
            "규정", "규정업", "010-3349-4187",
            "ENFP", userRequestTechStacks);

        UserJoinDTO userJoinDto2 = new UserJoinDTO("qqq@naver.com", "1234",
                "종영", "종영업", "010-1234-2222",
                "ENFP", userRequestTechStacks);

        List<TechStack> techStacks = techStackRepository.findAllByTechStackNameIn(userRequestTechStacks);

        User newUser1 = userJoinDto1.toEntity();
        User newUser2 = userJoinDto2.toEntity();
        newUser1.setTechStacks(techStacks);
        newUser2.setTechStacks(techStacks);
        userRepository.save(newUser1);
        userRepository.save(newUser2);
    }

    @Test
    void findByMbti() {
        User userA = new UserJoinDTO("kkk@naver.com", "1234",
                "규정", "규정업", "010-3349-4187",
                "ENFP", List.of()).toEntity();
        User userB = new UserJoinDTO("qqq@naver.com", "1234",
                "종영", "종영업", "010-1234-2222",
                "ENFP", List.of()).toEntity();

        userRepository.save(userA);
        userRepository.save(userB);

        List<User> findUser = userRepository.findByMbti(MBTI.ENFP);

        assertThat(findUser).contains(userA);
    }

    private List<TechStack> getTechStackFixtures(String ... techName) {
        return Stream.of(techName)
                .map(TechStack::new)
                .collect(Collectors.toList());
    }
}
