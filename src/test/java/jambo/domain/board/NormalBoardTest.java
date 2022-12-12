//package jambo.domain.board;
//
//import jambo.domain.TechStack;
//import jambo.domain.board.type.Category;
//import jambo.repository.BoardRepository;
//import jambo.repository.TechStackRepository;
//import jambo.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Commit;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@SpringBootTest
//@Transactional
//@Commit
//public class NormalBoardTest {
//
//    @Autowired
//    private BoardRepository boardRepository;
//
//
//    @Test
//    public void saveBoardTest() {
//
//
//        Board d = new NormalBoard(null,null,"바보","몽충이",null,null,0,false,Category.FREE_BOARD);
//        Board e = new NormalBoard(null,null,"바보","몽충이",null,null,0,false,Category.FREE_BOARD);
//        Board f = new NormalBoard(null,null,"바보","몽충이",null,null,0,false,Category.FREE_BOARD);
//        Board g = new NormalBoard(null,null,"바보1","몽충이2",null,null,0,false,Category.INFORMATION_BOARD);
//        Board h = new NormalBoard(null,null,"바보2","몽충이3",null,null,0,false,Category.INFORMATION_BOARD);
//        Board i = new NormalBoard(null,null,"바보4","몽충이6",null,null,0,false,Category.NOTICE_BOARD);
//        Board j = new NormalBoard(null,null,"바보5","몽충이4",null,null,0,false,Category.NOTICE_BOARD);
//        Board k = new NormalBoard(null,null,"바보12","몽충이123",null,null,0,false,Category.CONTEST_BOARD);
//        Board l = new NormalBoard(null,null,"바보33","몽충이123",null,null,0,false,Category.CONTEST_BOARD);
//
//        boardRepository.save(d);
//        boardRepository.save(e);
//        boardRepository.save(f);
//        boardRepository.save(g);
//        boardRepository.save(h);
//        boardRepository.save(i);
//        boardRepository.save(j);
//        boardRepository.save(k);
//        boardRepository.save(l);
//
//    }
//
//}
