package jambo.repository;

import jambo.domain.Comment;
import jambo.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findCommentById(Long id);
    List<Comment> findCommentsByBoardIdOrderByRegDate(Long id);

    @Query("delete from Comment r where r.board = :board")
    @Modifying
    void deleteByBoard(@Param("board") Board board);

    List<Comment> findByBoard(Board board);
}
