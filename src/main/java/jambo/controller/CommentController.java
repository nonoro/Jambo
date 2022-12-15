package jambo.controller;

import jambo.domain.Comment;
import jambo.domain.user.User;
import jambo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {

    private final CommentService commentService;

    /**
     * 등록하기
     */
    @RequestMapping("/insert")
    public String saveComment( Comment comment,  Long parentId, @AuthenticationPrincipal User user){
        commentService.saveComment(parentId, comment, user);
        return "redirect:/board/read/"+parentId+"/0/0?flag=1";
    }

    /**
     * 스터디보드에 등록하기
     */
    @RequestMapping("/insertCommentToStudyBoard")
    public String saveStudyBoardComment(Comment comment,  Long idForStudyBoard, @AuthenticationPrincipal User user){
        commentService.saveStudyBoardComment(idForStudyBoard, comment, user);

        return "redirect:/StudyBoard/read/"+idForStudyBoard+"/0/0?flag=1";
    }

    /**
     * 삭제하기
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        Comment comment = commentService.delete(id);

        return "redirect:/board/read/"+comment.getBoard().getId()+"/0/0?flag=1";
    }

    @RequestMapping("/deleteStudyBoard/{id}")
    public String deleteStudyBoard(@PathVariable Long id){
        Comment comment = commentService.delete(id);

        return "redirect:/StudyBoard/read/"+comment.getBoard().getId()+"/0/0?flag=1";
    }
}


