package jambo.domain.board;

import jambo.domain.TechStack;
import jambo.domain.board.type.Category;
import jambo.domain.user.User;
import jambo.dto.StudyBoardDTO;
import jambo.dto.UserJoinDTO;
import jambo.repository.BoardRepository;
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

@SpringBootTest
@Transactional
@Commit
public class StudyBoardTechStackTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechStackRepository techStackRepository;

    @Test
    public void saveBoardTest() {
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

        User user = userRepository.findById(1L).orElse(null);
        List<String> studyBoardTechStack = List.of("Java", "C", "Python");

        List<TechStack> techStacks2 = techStackRepository.findAllByTechStackNameIn(studyBoardTechStack);

        StudyBoardDTO newStudyBoard = new StudyBoardDTO(user, "안녕하세요", "너무너무 재미있어요",
                 6, "3개월"
                , "오픈카톡", "오프라인", "2022-12-19" , "스터디게시판"
                );

        StudyBoard studyBoard = newStudyBoard.toEntity();
        System.out.println("studyBoard.getCategory() = " + studyBoard.getCategory());
        studyBoard.setTechStacks(techStacks2);

        boardRepository.save(studyBoard);
    }

    private List<TechStack> getTechStackFixtures(String ... techName) {
        return Stream.of(techName)
                .map(TechStack::new)
                .collect(Collectors.toList());
    }

    @Test
    public void mappingTest() {
        Category.mapping("스터디게시판");
    }
}
