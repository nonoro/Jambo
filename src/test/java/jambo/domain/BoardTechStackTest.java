package jambo.domain;

import jambo.domain.board.Board;
import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import jambo.dto.StudyBoardDTO;
import jambo.repository.BoardRepository;
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
public class BoardTechStackTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveBoardTest() {
        User user = userRepository.findById(1L).orElse(null);
//        StudyBoardDTO newStudyBoard = new StudyBoardDTO(user, "안녕하세요", "너무너무 재미있어요", 0, );

//        boardRepository.save()
    }

    private List<TechStack> getTechStackFixtures(String ... techName) {
        return Stream.of(techName)
                .map(TechStack::new)
                .collect(Collectors.toList());
    }

}
