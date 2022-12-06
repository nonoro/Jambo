package jambo.repository;


import jambo.domain.board.NormalBoard;
import jambo.domain.board.type.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NormalBoardRepository extends JpaRepository<NormalBoard, Long> {

    List<NormalBoard> findAllByCategory(Category category);

    List<NormalBoard> findNormalBoardsByCategory(Category category);

}
