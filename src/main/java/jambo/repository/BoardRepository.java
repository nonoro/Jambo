package jambo.repository;

import jambo.domain.board.Board;
import jambo.domain.board.type.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BoardRepository extends JpaRepository<Board, Long> {

    /**
     * 조회수 증가
     */
    @Query("update Board b set b.views = b.views + 1 where b.id = ?1")
    @Modifying
    void updateViews(Long id);

    @Query("select b from Board b where b.category = :category order by b.writeDate DESC")
    Page<Board> serachByCategory(@Param("category")Category category, Pageable page);
}
