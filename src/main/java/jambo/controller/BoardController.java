package jambo.controller;

import jambo.domain.board.NormalBoard;
import jambo.domain.board.type.Category;
import jambo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    @Autowired
    private BoardService boardService;

    @RequestMapping("/list")
    private String list(@RequestParam Category category, Model model){
        log.debug("들우옴!");
        System.out.println("con category"+category);
        List<NormalBoard> boards = boardService.findAll(category);
        System.out.println("보드 사이즈 con"+boards.size());
        model.addAttribute("list", boards);
        return "Board/"+category;
    }
    /**
     * 게시글 상세보기 + 조회수 증가
     * */
    @RequestMapping("/read/{id}")
    public String read(@PathVariable Long id, String flag, Model model) {
        boolean state = flag == null ? true : false;
        NormalBoard dbBoard = boardService.read(id, state);
        model.addAttribute("board", dbBoard);
        return "Board/BoardRead";
    }
}
