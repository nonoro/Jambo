package jambo.controller;

import jambo.domain.user.Note;
import jambo.domain.user.User;
import jambo.service.NoteService;
import jambo.service.PaginationService;
import jambo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;
    private final PaginationService paginationService;

    /**
     * 받은 쪽지함
     */
//    @RequestMapping("/list")
//    public String list(Model model, @AuthenticationPrincipal User user){
//        String ReceiveUserEmail = user.getEmail();
//        List<Note> noteList = noteService.selectAll(ReceiveUserEmail);
//        model.addAttribute("noteList", noteList);
//
//        return "note/list";//뷰리턴
//    }
    @RequestMapping("/list")
    public String list(Model model, @AuthenticationPrincipal User user, @PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable pageable){
        String ReceiveUserEmail = user.getEmail();
        Page<Note> notes = noteService.selectAll(ReceiveUserEmail, pageable);
        List<Integer> pageNumbers = paginationService.pagination(pageable.getPageNumber(), notes.getTotalPages());
        model.addAttribute("notes", notes);
        model.addAttribute("pageNumbers", pageNumbers);
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
        User dbUser = userService.myPage(user.getId());
        noteService.insert(note, dbUser);

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
