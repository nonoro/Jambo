package jambo.repository;

import jambo.domain.board.Board;
import jambo.domain.board.Report;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findReportByUserAndBoard(User user, Board board);

}
