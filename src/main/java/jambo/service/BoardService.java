package jambo.service;

import jambo.domain.board.Board;
import jambo.domain.board.NormalBoard;
import jambo.domain.board.type.Category;
import jambo.repository.BoardRepository;
import jambo.repository.NormalBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private NormalBoardRepository normalBoard;

//    public List<Board> findAll(Category category){
    public List<NormalBoard> findAll(Category category){
//    public List<Board> findAll(C){


//        return boardRepository.findAllByCategory(category);
//        return normalBoard.findAllByCategory(category);
        return normalBoard.findNormalBoardsByCategory(category);
//        return boardRepository.findAll();
    }
}
