package jambo.service;


import jambo.domain.board.Board;

import jambo.dto.StudyBoardDTO;
import jambo.repository.StudyBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class StudyBoardService {

    @Autowired
    private StudyBoardRepository studyBoardRepository;

    public void insert(StudyBoardDTO studyBoardDTO){
        Board board = studyBoardDTO.toEntity();
        studyBoardRepository.save(board);
    }
}
