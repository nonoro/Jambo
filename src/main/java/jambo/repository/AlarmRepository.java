package jambo.repository;

import jambo.domain.Alarm;
import jambo.domain.Comment;
import jambo.domain.user.Note;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByUserAndIsReadOrderByReceivedTimeDesc(User user, boolean isRead);

    void deleteByNote(Note note);

    @Query("delete from Alarm a where a.comment = :comment")
    @Modifying
    void deleteByComment(@Param("comment") Comment comment);

    Optional<Alarm> findByComment(Comment comment);

    Optional<Alarm> findByNote(Note note);
}
