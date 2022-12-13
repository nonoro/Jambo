package jambo.controller;

import jambo.domain.board.Board;
import jambo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/list")
    public String NoticeList(Model model) {
        List<Board> list = noticeService.selectAll(); // list 변수에 결과 값을 담는다

//        model.addAttribute("list", list); // model에 데이터 값을 담는다
        return "notice/list"; // Notice/list로 이동
    }
}
