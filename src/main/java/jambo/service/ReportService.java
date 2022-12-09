package jambo.service;

import jambo.domain.board.Board;
import jambo.domain.board.Report;
import jambo.domain.board.type.ReportType;
import jambo.domain.user.User;
import jambo.repository.BoardRepository;
import jambo.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {
    private final ReportRepository reportRepository;
    private final BoardRepository boardRepository;

    public Report checkReport(User user, Board board){
        return reportRepository.findReportByUserAndBoard(user, board).orElse(null);
    }

    public void reportBoardByUser(Long boardId, User user, ReportType reportType){
        Board dbBoard = boardRepository.findBoardById(boardId);
        Report report = new Report(user, dbBoard);
        report.setReportType(reportType);
        reportRepository.save(report);
    }
}
