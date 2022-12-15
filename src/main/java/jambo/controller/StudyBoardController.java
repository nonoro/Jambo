package jambo.controller;


import jambo.domain.Comment;
import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import jambo.dto.StudyBoardDTO;
import jambo.service.CommentService;
import jambo.service.FileService;
import jambo.service.StudyBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/StudyBoard")
public class StudyBoardController {

    private final StudyBoardService service;

    private final CommentService commentService;

    private final FileService fileService;


    @RequestMapping("/StudyBoardMain")
    public String main(Model model) {
        List<StudyBoard> boards = service.selectAll();
        model.addAttribute("list", boards);
        model.addAttribute("savePath", fileService.getUrlPath());
        return "StudyBoard/StudyBoardMain";
    }

    @RequestMapping("/StudyBoardWrite")
    public String openWriteForm() {

        return "StudyBoard/StudyBoardWriteForm";
    }

    @RequestMapping("/insert")
    public String studyBoardInsert(StudyBoardDTO studyBoardDTO, @AuthenticationPrincipal User user, Model model) throws IOException {

        service.insert(studyBoardDTO, user);
        System.out.println("con studyBoardDTO = " + studyBoardDTO);

        return "redirect:/StudyBoard/StudyBoardMain";
    }

    @RequestMapping("/read/{id}/{isRead}/{commentId}")
    public String read(@PathVariable Long id, String flag, Model model, @AuthenticationPrincipal User user, @PathVariable int isRead, @PathVariable Long commentId) {
        boolean state = flag == null ? true : false;
//        Board dbBoard = boardService.findBoardById(id);
        StudyBoard dbStudyBoard = service.read(id, state, isRead, commentId);
        List<StudyBoard> boards = service.selectAll();

        List<Comment> commentsByBoardId = commentService.findCommentsByBoardId(id);
        model.addAttribute("comments", commentsByBoardId);
        model.addAttribute("savePath", fileService.getUrlPath());
        model.addAttribute("authUser", user);
        model.addAttribute("board", dbStudyBoard);
//        model.addAttribute("studyboard", dbStudyBoard);
        model.addAttribute("boardStacks", boards);

        return "StudyBoard/StudyBoardRead";
    }

    @GetMapping("/finishedRecruiting")
    @ResponseBody
    public int finishedRecruiting(Long id){
        service.finishedRecruiting(id);

        return 0;
    }
}
