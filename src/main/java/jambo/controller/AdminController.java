package jambo.controller;

import jambo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/adminMain")
    public String openAdminMainPage(Model model){
        int totalUsers = adminService.countUsers();
        int todayJoinUsers = adminService.countUsersByJoinToday();
        int totalNormalBoards = adminService.countNormalBoard();
        int todayNormalBoards = adminService.countNormalBoardByToday();
        int totalStudyBoards = adminService.countStudyBoard();
        int todayStudyBoards = adminService.countStudyBoardByToday();
        int totalReportedBoards = adminService.countReportedBoards();
        int todayReportedBoards = adminService.countReportedBoardsByToday();

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("todayJoinUsers", todayJoinUsers);
        model.addAttribute("totalNormalBoards", totalNormalBoards);
        model.addAttribute("todayNormalBoards", todayNormalBoards);
        model.addAttribute("totalStudyBoards", totalStudyBoards);
        model.addAttribute("todayStudyBoards", todayStudyBoards);
        model.addAttribute("totalReportedBoards", totalReportedBoards);
        model.addAttribute("todayReportedBoards", todayReportedBoards);


        return "/admin/adminMain";
    }

    @GetMapping("/iconRegister")
    public String openIconRegisterForm(){

        return "/admin/iconRegister";
    }

    /**
     * 게시글 작성폼 열기
     * */
    @RequestMapping("/openWriteForm")
    public String openWriteForm() {

        return "/admin/BoardWriteForm";
    }
}
