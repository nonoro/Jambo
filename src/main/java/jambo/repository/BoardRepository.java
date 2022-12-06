package jambo.repository;

import jambo.domain.board.Board;
import jambo.domain.board.type.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    /**
     * 전체 게시글 조회
     */
    List<Board> findAllByCategory(Category category);
//    List<Board> findAllByCategory(String category);

    List<Board> findAll();
}
