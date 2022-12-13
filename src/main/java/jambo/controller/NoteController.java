package jambo.controller;

import jambo.domain.user.Note;
import jambo.domain.user.User;
import jambo.service.NoteService;
import jambo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String list(Model model, @AuthenticationPrincipal User user){
        String ReceiveUserEmail = user.getEmail();
        List<Note> noteList = noteService.selectAll(ReceiveUserEmail);
        model.addAttribute("noteList", noteList);

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
    public String insert(Note note, @AuthenticationPrincipal User user){
        //보내는 사람은 로그인한 나인거지 그치...
        noteService.insert(note, user);

        return "redirect:/note/list";//url리턴 - 리다이렉트 or 포워드(모델)
    }

    /**
     * 쪽지 상세보기
     */
    @RequestMapping("/read")
    public String read(Model model, Long noteId, Boolean isRead) {
        Note note = noteService.selectBy(noteId);
        model.addAttribute("note", note);
        return "note/read";
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
