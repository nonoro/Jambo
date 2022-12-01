package jambo.repository;

import jambo.domain.board.ImgFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ImgFileRepository extends JpaRepository<ImgFile, Long> {
    List<ImgFile> findByFileNameIn(Collection<String> fileNames);
}
