package jambo.repository;

import jambo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findCommentById(Long id);
    List<Comment> findCommentsByBoardIdOrderByRegDate(Long id);
}
