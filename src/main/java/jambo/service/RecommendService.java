package jambo.service;

import jambo.domain.board.Board;
import jambo.domain.board.Recommendation;
import jambo.domain.user.User;
import jambo.repository.BoardRepository;
import jambo.repository.RecommendRepository;
import jambo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class RecommendService {
    private final RecommendRepository recommendRepository;

    private final UserRepository userRepository;

    private final BoardRepository boardRepository;

    public Recommendation checkRecommendation(User user, Board board){
        return recommendRepository.findRecommendationByUserAndBoard(user, board).orElse(null);
    }

    public void recommendUp(User user, Board board){

        Recommendation recommendation = new Recommendation(user,board);
        recommendRepository.save(recommendation);

    }

    public void recommendDown(Long userId, Long boardId) {

        User dbUser = userRepository.findById(userId).get();
        Board dbBoard = boardRepository.findBoardById(boardId);

        recommendRepository.deleteByUserAndBoard(dbUser, dbBoard);
    }

    public int countRecommendation(Board board){

         return recommendRepository.countByBoardId(board.getId());

    }

}
