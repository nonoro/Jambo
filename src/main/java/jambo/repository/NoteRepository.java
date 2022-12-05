package jambo.repository;

import jambo.domain.user.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findNotesByReceiveUser(String email); //받은 쪽지함 조회(받는 사람 = 나)
}
