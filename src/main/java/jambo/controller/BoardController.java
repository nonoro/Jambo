package jambo.controller;

import jambo.domain.Comment;
import jambo.domain.admin.Admin;
import jambo.domain.board.Board;
import jambo.domain.board.NormalBoard;
import jambo.domain.board.Recommendation;
import jambo.domain.board.Report;
import jambo.domain.board.type.Category;
import jambo.domain.board.type.ReportType;
import jambo.domain.user.User;
import jambo.domain.user.type.MBTI;
import jambo.dto.NormalBoardDTO;

import jambo.service.*;

import jambo.service.BoardService;
import jambo.service.CommentService;
import jambo.service.FileService;
import jambo.service.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

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
    private final CommentService commentService;


    private final ReportService reportService;

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

        List<Comment> dbComments = commentService.findCommentsByBoardId(id);
        model.addAttribute("comments", dbComments);

        /*신고하기 유무체크*/
        Report report = reportService.checkReport(user, dbBoard);
        model.addAttribute("report", report);
        return "/Board/BoardRead";
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
        System.out.println("normalBoardDTO = " + normalBoardDTO + ", user = " + user);
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
     * 수정하기 폼 열기
     * */
    @GetMapping("/update/{id}")
    public String openUpdateForm(@PathVariable Long id,Model model, @AuthenticationPrincipal User user){
        NormalBoard dbBoard = boardService.read(id, false);
        model.addAttribute("board", dbBoard);
        model.addAttribute("savePath", fileService.getUrlPath());
        model.addAttribute("authUser", user);

        return "/Board/BoardUpdateForm";
    }

    /**
     * 게시글 수정 DB에 넣기
     * */
    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable Long id, NormalBoardDTO normalBoardDTO) throws IOException {
        boardService.update(normalBoardDTO, id);

        return "redirect:/board/list?category="+normalBoardDTO.getCategory();
    }

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
    public String report(@PathVariable Long id, @AuthenticationPrincipal User user, String report)
    {
        System.out.println("report = " + report);
        reportService.reportBoardByUser(id, user, ReportType.mapping(report));
        return "redirect:/board/read/" + id +"?flag=1";
    }

}
