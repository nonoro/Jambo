package jambo.repository;


import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface StudyBoardRepository extends JpaRepository<StudyBoard, Long> {

    /**
     * 스터디게시판 모든 글 조회
     */
    List<StudyBoard> findAll();

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
     * 추천수 증가
     */
//    @Query("update Board b set b.recommendation = b.recommendation + 1 where b.id = ?1")
//    @Modifying
//    void recommend(Long id);
}
