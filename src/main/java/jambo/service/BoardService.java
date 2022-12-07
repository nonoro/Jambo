package jambo.service;

import jambo.domain.board.NormalBoard;
import jambo.domain.board.type.Category;
import jambo.repository.BoardRepository;
import jambo.repository.NormalBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private NormalBoardRepository normalBoardRepository;

    /**
     * 카테고리에 맞는 전체 NormalBoard 검색
     * */
    public List<NormalBoard> findAll(Category category){

        return normalBoardRepository.findAllByCategory(category);
    }

    /**
     * NormalBoard 상세보기
     * */
    public NormalBoard read(Long id, Boolean state){
        if(state) {//조회수 증가
            boardRepository.updateViews(id);
        }
        return normalBoardRepository.findNormalBoardById(id);
    }
}
