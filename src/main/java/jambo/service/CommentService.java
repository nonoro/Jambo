package jambo.service;

import jambo.domain.Comment;
import jambo.domain.board.Board;
import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import jambo.dto.AlarmResponse;
import jambo.repository.BoardRepository;
import jambo.repository.CommentRepository;
import jambo.repository.StudyBoardRepository;
import jambo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final StudyBoardRepository studyBoardRepository;
    private final UserRepository userRepository;
    private final AlarmService alarmService;

    public List<Comment> findCommentsByBoardId(Long id) {
        return commentRepository.findCommentsByBoardIdOrderByRegDate(id);
    }

    public void saveComment(Long boardId, Comment comment, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        comment.save(board, user);
        commentRepository.save(comment);
        /*댓글단 유저에게 포인트 5점 추가*/
        userRepository
                .findById(user.getId())
                .orElse(null)
                .addPoint(5);

        User writer = board.getUser();
        AlarmResponse response = AlarmResponse.comment(boardId + "번 게시글에 댓글이 달렸습니다.");
        alarmService.send(writer.getId(), response);
    }

    public void saveStudyBoardComment(Long id, Comment comment, User user) {
        StudyBoard studyBoardById = studyBoardRepository.findStudyBoardById(id);
        comment.save(studyBoardById, user);
        commentRepository.save(comment);
        /*댓글단 유저에게 포인트 5점 추가*/
        userRepository
                .findById(user.getId())
                .orElse(null)
                .addPoint(5);
    }

    public Comment delete(Long id) {
        Comment comment = commentRepository.findCommentById(id);
        comment.getUser().addPoint(-5);
        commentRepository.deleteById(id);
        return comment;
    }
}
