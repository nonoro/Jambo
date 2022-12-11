package jambo.repository;

import jambo.domain.board.Board;
import jambo.domain.board.Report;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findReportByUserAndBoard(User user, Board board);

    /**
     * 총 신고된 게시글 수 조회 - 관리자
     */
    int countBy();

    /**
     * 오늘 신고된 게시글 수 조회 - 관리자
     */
    int countByReportDateBetween(LocalDateTime start, LocalDateTime end);

}
