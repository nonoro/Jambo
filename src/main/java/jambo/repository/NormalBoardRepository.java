package jambo.repository;


import jambo.domain.board.NormalBoard;
import jambo.domain.board.type.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NormalBoardRepository extends JpaRepository<NormalBoard, Long> {

    /**
     * 카테고리에 맞는 전체 보드 검색
     * */
    List<NormalBoard> findAllByCategory(Category category);

//    List<NormalBoard> findNormalBoardsByCategory(Category category);
    /**
     * 보드 상세보기
     * */
    NormalBoard findNormalBoardById(Long id);
}
