package jambo.repository;


import jambo.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyBoardRepository extends JpaRepository<Board, Long> {

    /**
     * 글 작성
     * */

}
