package jambo.service;

import jambo.domain.Alarm;
import jambo.domain.Comment;
import jambo.domain.board.Board;
import jambo.domain.user.User;
import jambo.dto.AlarmResponse;
import jambo.repository.AlarmRepository;
import jambo.repository.BoardRepository;
import jambo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRep;
    private final BoardRepository boardRepository;

    private final AlarmRepository alarmRepository;

    private final AlarmService alarmService;

    public List<Comment> findCommentsByBoardId(Long id){
        return commentRep.findCommentsByBoardIdOrderByRegDate(id);
    }

    public void saveComment(Long boardId, Comment comment, User user) {
            Board board = boardRepository.findById(boardId).orElseThrow();
            comment.save(board, user);
            commentRep.save(comment);

        User writer = board.getUser();

        alarmRepository.save(new Alarm(writer, comment));
        AlarmResponse response = AlarmResponse.comment(boardId + "번 게시글에 댓글이 달렸습니다.");
        alarmService.send(writer.getId(), response);
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
