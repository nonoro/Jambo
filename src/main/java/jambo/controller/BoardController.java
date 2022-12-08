package jambo.controller;

import jambo.domain.board.NormalBoard;
import jambo.domain.board.type.Category;
import jambo.domain.user.User;
import jambo.dto.NormalBoardDTO;
import jambo.dto.StudyBoardDTO;
import jambo.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
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
    /**
     * 게시글 작성폼 열기
     * */
    @RequestMapping("/write")
    public String openWriteForm() {

        return "/Board/BoardWriteForm";
    }
    /**
     * 게시글 작성 DB 에 넣기
     * */
//    @RequestMapping("/insert")
//    public String studyBoardInsert(NormalBoard normalBoard, Category category) throws IOException {
//        normalBoard.setCategory(category);
//        boardService.insert(normalBoard);
//        System.out.println("con normalBoard = " + normalBoard);
//        return "redirect:/board/list?category="+category;
//    }

    @RequestMapping("/insert")
    public String studyBoardInsert(NormalBoardDTO normalBoardDTO, @AuthenticationPrincipal User user) throws IOException {
        boardService.insert(normalBoardDTO, user);
        return "redirect:/board/list?category="+normalBoardDTO.getCategory();
    }
}
