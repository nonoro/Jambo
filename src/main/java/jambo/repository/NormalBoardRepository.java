package jambo.repository;


import jambo.domain.board.NormalBoard;
import jambo.domain.board.type.Category;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NormalBoardRepository extends JpaRepository<NormalBoard, Long> {

    /**
     * 카테고리에 맞는 전체 보드 검색
     * */
    List<NormalBoard> findAllByCategory(Category category);

    /**
     * 보드 상세보기
     * */
    NormalBoard findNormalBoardById(Long id);

    /**
     * 내가쓴 모든 NomalBoard 조회
     */
    @Query("select b from NormalBoard b where b.user = :user")
    List<NormalBoard> SearchNomalBoardByEmail(@Param("user") User user);

    /**
     * Normal 게시글 수 조회 - 관리자
     */
    int countBy();

    /**
     * 오늘 작성된 게시글 수 조회 - 관리자
     */
    int countByWriteDateBetween(LocalDateTime start, LocalDateTime end);
}
