package jambo.service;

import jambo.domain.board.Board;

import java.util.List;

public interface NoticeService {

    // 게시물 목록 조회
    public List<Board> selectAll();

}
