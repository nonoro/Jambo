package jambo.repository;

import jambo.domain.board.Board;
import jambo.domain.board.Recommendation;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecommendRepository extends JpaRepository<Recommendation, Long> {

    Optional<Recommendation> findRecommendationByUserAndBoard(User user, Board board);

    int countByBoardId(Long boardId);

    void deleteByUserAndBoard(User user, Board board);

    @Query("delete from Recommendation r where r.board = :board")
    @Modifying
    void deleteByBoard(@Param("board") Board board);
}
