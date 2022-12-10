package jambo.service;

import jambo.domain.Comment;
import jambo.domain.board.Board;
import jambo.domain.board.type.Category;
import jambo.domain.user.User;
import jambo.dto.StudyBoardDTO;
import jambo.repository.BoardRepository;
import jambo.repository.CommentRepository;
import jambo.repository.UserRepository;
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

    private final UserRepository userRepository;

    public List<Comment> findCommentsByBoardId(Long id) {
        return commentRep.findCommentsByBoardIdOrderByRegDate(id);
    }

    public void saveComment(Long id, Comment comment, User user) {
        Board board = boardRepository.findById(id).orElseThrow();
        comment.save(board, user);
        commentRep.save(comment);
        /*댓글단 유저에게 포인트 5점 추가*/
        userRepository
                .findById(user.getId())
                .orElse(null)
                .addPoint(5);
    }


    public Comment delete(Long id) {
        Comment comment = commentRep.findCommentById(id);
        comment.getUser().addPoint(-5);
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
