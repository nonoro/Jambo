package jambo.service;

import jambo.domain.Alarm;
import jambo.domain.Comment;
import jambo.domain.board.Board;
import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import jambo.dto.AlarmResponse;
import jambo.repository.*;
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

    private final AlarmRepository alarmRepository;

    public List<Comment> findCommentsByBoardId(Long id) {
        return commentRepository.findCommentsByBoardIdOrderByRegDate(id);
    }

    public void saveComment(Long boardId, Comment comment, User user) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        comment.save(board, user);
        Comment saveComment = commentRepository.save(comment);
        /*댓글단 유저에게 포인트 5점 추가*/
        userRepository
                .findById(user.getId())
                .orElse(null)
                .addPoint(5);

        alarmRepository.save(new Alarm(board.getUser(), saveComment));

        User writer = board.getUser();
        AlarmResponse response = AlarmResponse.comment(boardId + "번 게시글에 댓글이 달렸습니다.");
        alarmService.send(writer.getId(), response);
    }

    public void saveStudyBoardComment(Long id, Comment comment, User user) {
        StudyBoard studyBoardById = studyBoardRepository.findStudyBoardById(id);
        comment.save(studyBoardById, user);
        commentRepository.save(comment);
        Comment saveComment = commentRepository.save(comment);
        /*댓글단 유저에게 포인트 5점 추가*/
        userRepository
                .findById(user.getId())
                .orElse(null)
                .addPoint(5);


        alarmRepository.save(new Alarm(studyBoardById.getUser(), saveComment));


        User writer = studyBoardById.getUser();
        AlarmResponse response = AlarmResponse.comment(studyBoardById + "번 게시글에 댓글이 달렸습니다.");
        alarmService.send(writer.getId(), response);
    }

    public Comment delete(Long id) {
        Comment comment = commentRepository.findCommentById(id);
        comment.getUser().addPoint(-5);
        alarmRepository.deleteByComment(comment);
        commentRepository.deleteById(id);
        return comment;
    }
}
