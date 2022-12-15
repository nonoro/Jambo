package jambo.service;


import jambo.domain.TechStack;
import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import jambo.dto.StudyBoardDTO;
import jambo.repository.BoardRepository;
import jambo.repository.StudyBoardRepository;
import jambo.repository.TechStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class StudyBoardService {


    private final TechStackRepository techStackRepository;

    private final StudyBoardRepository studyBoardRepository;

    private final BoardRepository boardRepository;

    public void insert(StudyBoardDTO studyBoardDTO, User user){
        List<String> studyBoardTechStacks = studyBoardDTO.getTechStacks();
        StudyBoard board = studyBoardDTO.toEntity();
        board.setUser(user);
        if(studyBoardTechStacks != null){
            List<TechStack> techStacks = techStackRepository.findAllByTechStackNameIn(studyBoardTechStacks);
            board.setTechStacks(techStacks);
        }
        boardRepository.save(board);
    }

    public List<StudyBoard> selectAll(){

        return studyBoardRepository.findAllByOrderByWriteDateDesc();
    }

    public StudyBoard read(Long id, boolean state, int isRead, Long commendId){
        if(state) {//조회수 증가
            boardRepository.updateViews(id);
        }
        return studyBoardRepository.findStudyBoardById(id);
    }

    /**
     * 내가쓴 모든 StudyBoard 조회
     */
    public List<StudyBoard> showStudyBoard(User user) {
        return studyBoardRepository.SearchStudyBoardByEmail(user);
    }

    public void finishedRecruiting(Long id){
        StudyBoard dbStudyBoard = studyBoardRepository.findStudyBoardById(id);
        dbStudyBoard.setRecruiting(false);
    }
}
