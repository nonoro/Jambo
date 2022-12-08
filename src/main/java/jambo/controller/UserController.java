package jambo.controller;


import jambo.domain.user.User;
import jambo.dto.UserJoinDTO;
import jambo.dto.LoginDTO;
import jambo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/preJoinForm")
    public void preJoinForm() {

    }

    @RequestMapping("/joinForm")
    public String join(Model model) {
        return "user/joinForm";
    }

    @RequestMapping("/idcheckAjax")
    @ResponseBody
    public HashMap<String, Object> idCheckAjax(String email) {
        System.out.println("con : " + email);

        return userService.userEmailOverlap(email);
    }

    @RequestMapping("/join")
    public String join(UserJoinDTO userJoinDTO) {
        userService.join(userJoinDTO);
        return "redirect:/user/preJoinForm";
    }

    @RequestMapping("/loginForm")
    public String loginForm(Model model, @AuthenticationPrincipal User securityUser) {
        return "/user/loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

}
