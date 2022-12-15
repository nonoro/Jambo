package jambo.service;

import jambo.domain.Alarm;
import jambo.domain.Comment;
import jambo.domain.board.Board;
import jambo.domain.board.NormalBoard;
import jambo.domain.board.type.Category;
import jambo.domain.user.User;
import jambo.dto.NormalBoardDTO;
import jambo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final NormalBoardRepository normalBoardRepository;

    private final UserRepository userRepository;

    private final AlarmRepository alarmRepository;

    private final CommentRepository commentRepository;

    private final ReportRepository reportRepository;

    private final RecommendRepository recommendRepository;

    /**
     * 카테고리에 맞는 전체 NormalBoard 검색
     */
    public Page<Board> findAll(Category category, Pageable page) {
        return boardRepository.serachByCategory(category, page);
    }

    /**
     * NormalBoard 상세보기
     */
    public NormalBoard read(Long id, Boolean state, int isRead, Long commentId) {
        if (state) {//조회수 증가
            boardRepository.updateViews(id);
        }
        NormalBoard normalBoardById = normalBoardRepository.findNormalBoardById(id);
        Board board = boardRepository.findById(id).get();

        if (isRead == 1) {
            Comment comment = commentRepository.findById(commentId).get();
            Alarm alarm = alarmRepository.findByComment(comment).get();
            alarm.setRead(true);
        }

        return normalBoardById;
    }

    /**
     * 게시글 작성하기
     */
    public void insert(NormalBoardDTO normalBoardDTO, User user) {
        NormalBoard normalBoard = normalBoardDTO.toEntity(user);
        boardRepository.save(normalBoard);
        user.addPoint(30);

        userRepository.save(user);
    }

    /**
     * 게시글 삭제하기
     */
    public void delete(Long id) {
        Board board = boardRepository.findById(id).get();
        reportRepository.deleteByBoard(board);
        recommendRepository.deleteByBoard(board);
        List<Comment> comments = commentRepository.findByBoard(board);
        for (Comment comment : comments) {
            alarmRepository.deleteByComment(comment);
        }
        commentRepository.deleteByBoard(board);

        int result = boardRepository.deleteBoardById(id);
    }

    public Board findBoardById(Long id) {

        return boardRepository.findBoardById(id);
    }

    /**
     * 내가쓴 모든 NomalBoard 조회
     */
    public List<NormalBoard> showNormalBoard(User user) {

        return normalBoardRepository.SearchNomalBoardByEmail(user);
    }

    /**
     * 게시글 수정
     */
    public void update(NormalBoardDTO normalBoardDTO, Long id) {
        Board dbboard = boardRepository.findById(id).orElse(null);
        dbboard.setTitle(normalBoardDTO.getTitle());
        dbboard.setCategory(normalBoardDTO.getCategory());
        dbboard.setContent(normalBoardDTO.getContent());
    }

    /**
     * 신고된 게시글 관리자에 의해 리스트에서 숨기기
     */
    public void hideReportedBoard(Long id) {
        Board dbboard = boardRepository.findById(id).orElse(null);
        dbboard.setReported(true);
    }

    /**
     * 신고된 게시글 관리자에 의해 리스트에서 살리기
     */
    public void liveReportedBoard(Long id) {
        Board dbboard = boardRepository.findById(id).orElse(null);
        dbboard.setReported(false);
    }
}
