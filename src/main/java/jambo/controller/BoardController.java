package jambo.controller;

import jambo.domain.board.Board;
import jambo.domain.board.NormalBoard;
import jambo.domain.board.Recommendation;
import jambo.domain.board.type.Category;
import jambo.domain.user.User;
import jambo.dto.NormalBoardDTO;
import jambo.repository.UserRepository;
import jambo.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final FileService fileService;

    private final PaginationService paginationService;

    private final RecommendService recommendService;

    private final UserService userService;

    @RequestMapping("/list")
    private String list(@RequestParam Category category, Model model, @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable){

        Page<Board> boards = boardService.findAll(category, pageable);
        List<Integer> pageNumbers = paginationService.pagination(pageable.getPageNumber(), boards.getTotalPages());
        model.addAttribute("list", boards);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("savePath", fileService.getUrlPath());
        return "Board/"+category;
    }
    /**
     * 게시글 상세보기 + 조회수 증가
     * */
    @RequestMapping("/read/{id}")
    public String read(@PathVariable Long id, String flag, Model model, @AuthenticationPrincipal User user) {
        boolean state = flag == null ? true : false;
        NormalBoard dbBoard = boardService.read(id, state);
        model.addAttribute("board", dbBoard);
        model.addAttribute("savePath", fileService.getUrlPath());
        model.addAttribute("authUser", user);
        /* 추천수 조회 유무 체크용*/
        Recommendation recommendation = recommendService.checkRecommendation(user, dbBoard);
        model.addAttribute("recommendation", recommendation);
        int countRecommendation = recommendService.countRecommendation(dbBoard);
        model.addAttribute("countRecommendation",countRecommendation);
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
    @RequestMapping("/insert")
    public String studyBoardInsert(NormalBoardDTO normalBoardDTO, @AuthenticationPrincipal User user) throws IOException {
        boardService.insert(normalBoardDTO, user);

        return "redirect:/board/list?category="+normalBoardDTO.getCategory();
    }

    /**
     * 게시글 삭제 기능
     * */
    @GetMapping("/delete/{id}")
    public String boardDelete(@PathVariable Long id) {

        boardService.delete(id);
        return "index";
    }

    /**
     * 수정하기 (예정)
     * */

    /**
     * 추천수 증가
     * * */
    @GetMapping("/recommendUp/{id}")
    public String recommendUp(@PathVariable Long id, @AuthenticationPrincipal User user){
        User dbUser = userService.findUser(user);
        Board dbBoard = boardService.findBoardById(id);
        recommendService.recommendUp(dbUser, dbBoard);

        return "redirect:/board/read/" + id+"?flag=1";
    }

    /**
     * 추천수 감소
     * */
    @GetMapping("/recommendDown/{id}")
    public String recommendDown(@PathVariable Long id, @AuthenticationPrincipal User user){
        recommendService.recommendDown(user.getId(), id);
        return "redirect:/board/read/" + id+"?flag=1";
    }

    /**
     * 게시글 신고하기
     * */
    @RequestMapping("/report/{id}")
    public String report(@PathVariable Long id, @AuthenticationPrincipal User user){
        return null;
    }

}
