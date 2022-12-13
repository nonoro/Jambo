package jambo.repository;


import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface StudyBoardRepository extends JpaRepository<StudyBoard, Long> {

    /**
     * 스터디게시판 모든 글 조회
     */
    List<StudyBoard> findAllByOrderByWriteDateDesc();


    /**
     * 스터디 게시판 글 상세조회
     */
    StudyBoard findStudyBoardById(Long id);



    /**
     * 내가쓴 모든 StudyBoard 조회
     */
    @Query("select b from StudyBoard b where b.user = :user")
    List<StudyBoard> SearchStudyBoardByEmail(@Param("user") User user);


   /**
    * 스터디 게시글 수 조회
    * */
   int countBy();

    /**
     * 오늘 작성된 게시글 수 조회
     */
    int countByWriteDateBetween(LocalDateTime start, LocalDateTime end);
}
