package jambo.service;

import jambo.domain.board.Board;
import jambo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private BoardRepository noticeRepository;

    @Override
    public List<Board> selectAll() {

        return noticeRepository.findAll();
    }
}
