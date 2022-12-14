package jambo.controller;

import jambo.domain.user.Note;
import jambo.domain.user.User;
import jambo.service.NoteService;
import jambo.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final PaginationService paginationService;

    /**
     * 받은 쪽지함
     */
    @RequestMapping("/list")
    public String list(Model model, @AuthenticationPrincipal User user, @PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable pageable){
        String ReceiveUserEmail = user.getEmail();
        Page<Note> notes = noteService.selectAll(ReceiveUserEmail, pageable);
        List<Integer> pageNumbers = paginationService.pagination(pageable.getPageNumber(), notes.getTotalPages());
        model.addAttribute("notes", notes);
        model.addAttribute("pageNumbers", pageNumbers);

        return "note/list";
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
        noteService.insert(note, user);

        return "redirect:/note/list";//url리턴 - 리다이렉트 or 포워드(모델)
    }

    /**
     * 쪽지 상세보기
     */
    @RequestMapping("/read/{noteId}/{isRead}")
    public String read(Model model, @PathVariable Long noteId, @PathVariable int isRead) {
        Note note = noteService.selectBy(noteId, isRead);
        model.addAttribute("note", note);

        return "note/read";
    }

    /**
     * 삭제
     */
    @RequestMapping("/delete")
    public String delete(Long noteId){
        noteService.delete(noteId);

        return "redirect:/note/list";
    }
}
