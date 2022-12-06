package jambo.controller;

import jambo.domain.user.Note;
import jambo.domain.user.User;
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

    /**
     * 받은 쪽지함
     */
    @RequestMapping("/list")
    public String list(Model model, HttpSession session){
       String email = "yuna@naver.com";//세션에 저장된 사용자 이메일로 변경 예정
       List<Note> list = noteService.selectAll(email);
       model.addAttribute("list", list);
        System.out.println("list = " + list);
       return "note/list";//뷰리턴
    }

    /**
     * 등록폼
     */
    @RequestMapping("/write")
    public String write(){
        return "note/write";
    }

    /**
     * 쪽지 전송 : 쪽지 전송이 완료되면 받은 쪽지함으로 다시 돌아간다.
     */
    @PostMapping("/insert")
    public String insert(Note note, HttpSession session){
        String email = "yuna@naver.com";//세션에 저장된 사용자 이메일로 변경 예정
//        note.setSendUser(email);
        noteService.insert(note, email);
        System.out.println("노트인서트!!!!!!" +note);
        return "redirect:/note/list";//url리턴 - 리다이렉트 or 포워드(모델)
    }

    /**
     * 쪽지 상세보기
     */
    @RequestMapping("/read")
    public String read(Model model, Long noteId){
        Note note = noteService.selectBy(noteId);
        model.addAttribute("note", note);
        return "/note/read";
    }

    /**
     * 삭제
     */
    @RequestMapping("/delete")
    public String delete(Long noteId){
        noteService.delete(noteId);
        System.out.println("noteId = " + noteId);
        return "redirect:/note/list";
    }
}
