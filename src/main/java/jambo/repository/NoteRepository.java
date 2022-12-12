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

    //하고 싶은거 받은 시간이 최근인게 제일 위로 왔으면 좋겠음
    //select * from Note order BY SENT_TIME DESC;
    @Query("select n from Note n order by n.sentTime DESC")
    Page<Note> findNotesByReceiveUser(@Param("email")String email, Pageable page); //받은 사람

//    Page<Note> findNotesByReceiveUser(String email, Pageable page); //받은 사람

//    Page<Note> findAllByOrderBySentTimeDesc(Pageable page);


//    Page<Note> findAllByOrderBySentTimeIdDesc(Pageable page);

    Page<Note> findAllByOrderBySentTimeDesc(String email, Pageable page);


}
