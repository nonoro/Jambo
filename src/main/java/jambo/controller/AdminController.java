package jambo.controller;

import jambo.dto.AdminJoinDTO;
import jambo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @RequestMapping("/joinForm")
    public void join() {

    }

    @RequestMapping("/idcheckAjax")
    @ResponseBody
    public HashMap<String, Object> idCheckAjax(String email) {

        return adminService.adminEmailOverlap(email);
    }

    @RequestMapping("/join")
    public String join(AdminJoinDTO adminJoinDTO) {
        adminService.join(adminJoinDTO);
        return "redirect:/user/preJoinForm";
    }
}
