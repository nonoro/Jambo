package jambo.repository;

import jambo.domain.user.Note;
import jambo.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {


    Page<Note> findNotesByReceiveUserOrderBySentTimeDesc(String email, Pageable page);
}
