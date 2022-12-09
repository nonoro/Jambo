package jambo.controller;


import jambo.domain.board.Board;
import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import jambo.dto.StudyBoardDTO;
import jambo.service.StudyBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/StudyBoard")
public class StudyBoardController {

    @Autowired
    private StudyBoardService service;

    @RequestMapping("/StudyBoardMain")
    public String main(Model model) {
        List<StudyBoard> boards = service.selectAll();
        model.addAttribute("list", boards);

        return "/StudyBoard/StudyBoardMain";
    }

    @RequestMapping("/StudyBoardWrite")
    public String openWriteForm() {

        return "/StudyBoard/StudyBoardWriteForm";
    }

    @RequestMapping("/insert")
    public String studyBoardInsert(StudyBoardDTO studyBoardDTO, @AuthenticationPrincipal User user) throws IOException {

        service.insert(studyBoardDTO, user);
        System.out.println("con studyBoardDTO = " + studyBoardDTO);

        return "StudyBoard/StudyBoardMain";
    }

    @RequestMapping("/read/{id}")
    public ModelAndView read(@PathVariable Long id, String flag) {
        boolean state = flag == null ? true : false;
        StudyBoard dbBoard = service.read(id, state);
        return new ModelAndView("StudyBoard/StudyBoardRead", "board", dbBoard);

    }
}
