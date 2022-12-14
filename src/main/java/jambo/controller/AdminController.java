package jambo.controller;

import jambo.domain.board.Report;
import jambo.domain.user.User;
import jambo.dto.AdminJoinDTO;
import jambo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final PaginationService paginationService;
    private final FileService fileService;
    private final BoardService boardService;

    @GetMapping("/adminMain")
    public void openAdminMainPage(Model model, @PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable pageable){
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

        Page<User> dbAllUsers = adminService.findAllUser(pageable);
        List<Integer> pageNumbers = paginationService.pagination(pageable.getPageNumber(), dbAllUsers.getTotalPages());
        String dbFile = fileService.getUrlPath();
        model.addAttribute("list", dbAllUsers);
        model.addAttribute("savePath", dbFile);
        model.addAttribute("pageNumbers", pageNumbers);
    }

    @GetMapping("/iconRegister")
    public String openIconRegisterForm(){

        return "admin/iconRegister";
    }

    /**
     * 게시글 작성폼 열기
     * */
    @RequestMapping("/openWriteForm")
    public String openWriteForm() {

        return "admin/BoardWriteForm";
    }

    @GetMapping("/ReportPage")
    public void openReportPage(Model model, @PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable){
        int totalReportedBoards = adminService.countReportedBoards();
        int todayReportedBoards = adminService.countReportedBoardsByToday();
        model.addAttribute("totalReportedBoards", totalReportedBoards);
        model.addAttribute("todayReportedBoards", todayReportedBoards);

        Page<Report> reports = adminService.findAll(pageable);
        List<Integer> pageNumbers = paginationService.pagination(pageable.getPageNumber(), reports.getTotalPages());
        model.addAttribute("list", reports);
        model.addAttribute("pageNumbers", pageNumbers);
    }

    @GetMapping("/joinForm")
    public String openAdminJoinForm(){
        return "admin/adminJoin";
    }

    @PostMapping("/join")
    public String join(AdminJoinDTO adminJoinDTO){
        adminService.join(adminJoinDTO);

        return "redirect:/admin/adminMain";
    }

    @RequestMapping("/idCheckAjax")
    @ResponseBody
    public HashMap<String, Object> idCheckAjax(String email) {
        System.out.println("con : " + email);

        return adminService.adminEmailOverlap(email);
    }

    /**
     * 신고된 게시글 리스트에서 숨키기 - by 관리자
     * */

    @GetMapping("/hideBoard/{id}")
    private String hideReportedBoard(@PathVariable Long id){
        boardService.hideReportedBoard(id);

        return "redirect:/admin/ReportPage";
    }

    /**
     * 신고된 게시글 리스트에서 살리기 - by 관리자
     * */

    @GetMapping("/liveBoard/{id}")
    private String liveReportedBoard(@PathVariable Long id){
        boardService.liveReportedBoard(id);

        return "redirect:/admin/ReportPage";
    }
}
