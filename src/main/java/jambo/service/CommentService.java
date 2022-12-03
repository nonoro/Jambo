package jambo.service;

import jambo.domain.Comment;
import jambo.domain.board.Board;
import jambo.domain.board.type.Category;
import jambo.domain.user.User;
import jambo.dto.StudyBoardDTO;
import jambo.repository.BoardRepository;
import jambo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRep;
    private final BoardRepository boardRepository;

    public List<Comment> findCommentsByBoardId(Long id){
        return commentRep.findCommentsByBoardIdOrderByRegDate(id);
    }

    public void saveComment(Long id, Comment comment, User user){
            Board board = boardRepository.findById(id).orElseThrow();
            comment.save(board, user);
            commentRep.save(comment);
    }


    public Comment delete(Long id) {
        Comment comment = commentRep.findCommentById(id);
        commentRep.deleteById(id);
        return comment;
    }

    /**
     * 댓글 수정 기능(예정)
     */
//    public void update(Long id, Comment comment, User user){
//        Comment updateComment = commentRep.findById(id).orElseThrow();
//        updateComment.save(updateComment.getBoard(), user);
//    }


}
