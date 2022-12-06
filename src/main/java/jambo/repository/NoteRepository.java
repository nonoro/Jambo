package jambo.repository;

import jambo.domain.user.Note;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findNotesByReceiveUser(String email); //받은 사람

}
