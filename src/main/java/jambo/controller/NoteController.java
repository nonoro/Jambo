package jambo.controller;

import jambo.domain.user.Note;
import jambo.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @RequestMapping("/list")
    public String list(Model model, HttpSession session){
       String email = "yuna@naver.com";
       List<Note> list = noteService.selectAll(email);
       model.addAttribute("list", list);
       return "note/list";//뷰리턴
    }

    @RequestMapping("/write")
    public String write(){
        return "note/write";
    }

    /**
     * 쪽지 전송 : 쪽지 전송이 완료되면 받은 쪽지함으로 다시 돌아간다.
     */
    @PostMapping("/insert")
    public String insert(Note note){
        noteService.insert(note);
        return "redirect:/list";//url리턴 - 리다이렉트 or 포워드(모델)
    }

}
