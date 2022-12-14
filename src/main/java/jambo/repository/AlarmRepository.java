package jambo.repository;

import jambo.domain.Alarm;
import jambo.domain.Comment;
import jambo.domain.user.Note;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByUserAndIsReadOrderByReceivedTimeDesc(User user, boolean isRead);

    void deleteByNote(Note note);

    void deleteByComment(Comment comment);
}
