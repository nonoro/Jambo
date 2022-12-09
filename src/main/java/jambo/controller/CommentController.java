package jambo.controller;

import jambo.domain.Comment;
import jambo.domain.user.User;
import jambo.service.BoardService;
import jambo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {

    private final CommentService commentService;
    private final BoardService boardService;


    /**
     * 등록하기
     */
    @RequestMapping("/insert")
    public String saveComment( Comment comment,  Long parentId, Model model, @AuthenticationPrincipal User user){
        commentService.saveComment(parentId, comment, user);
        return "redirect:/board/read/"+parentId+"?flag=1";
    }

    /**
     * 삭제하기
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        Comment comment = commentService.delete(id);
        return "redirect:/board/read/"+comment.getBoard().getId()+"?flag=1";
    }

//    /**
//     * 댓글 수정하기(예정)
//     */
//    @RequestMapping("/update")
//    public String update(Comment comment, Long parentId, @AuthenticationPrincipal User user){
//        commentService.update(parentId, comment, user);
//        return "redirect:/board/read/"+parentId+"?flag=1";
//    }


}


