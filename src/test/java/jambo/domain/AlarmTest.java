package jambo.domain;

import jambo.domain.board.Board;
import jambo.domain.board.NormalBoard;
import jambo.domain.user.Note;
import jambo.domain.user.User;
import jambo.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static jambo.domain.board.type.Category.FREE_BOARD;

@SpringBootTest
@Transactional
public class AlarmTest {

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void save() {
        User user1 = new User("kgu1022@naver.com", "1234", "권규정", "nonoro", "010-9316-1455", "enfp");
        userRepository.save(user1);

        User user2 = new User("kkk@naver.com", "1234", "김영철", "스윙스", "010-1293-1455", "enfp");
        userRepository.save(user2);

        Board normalBoard = new NormalBoard(user1, "제목없음", "제곧내", FREE_BOARD);
        boardRepository.save(normalBoard);

        Comment comment = new Comment(normalBoard, user2, "너무좋아요!!");
        commentRepository.save(comment);

        Note note = new Note(user2.getEmail(), user1.getEmail(), "안녕");
        noteRepository.save(note);


        Alarm commentAlarm = new Alarm(user1, comment);
        alarmRepository.save(commentAlarm);

        Alarm noteAlarm = new Alarm(user1, note);
        alarmRepository.save(noteAlarm);
    }

    @Test
    public void findUserAlarm() {
        User user = userRepository.findById(1L).get();
        List<Alarm> byUser = alarmRepository.findByUserAndIsReadOrderByReceivedTimeDesc(user, false);

        for (Alarm alarm : byUser) {
            System.out.println("alarm = " + alarm);
            if (alarm.getComment() == null) {
                System.out.println("[" + alarm.getAlarmType() + "] " + alarm.getNote().getSendUser() + "에게 온 쪽지입니다");
            } else {
                System.out.println("[" + alarm.getAlarmType() + "] " + alarm.getComment().getBoard().getTitle() + "에 달린 댓글");
            }
        }
    }
}
