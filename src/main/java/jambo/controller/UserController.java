package jambo.controller;


import jambo.dto.UserJoinDTO;
import jambo.dto.LoginDTO;
import jambo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public void join() {

    }

    @RequestMapping("/idcheckAjax")
    @ResponseBody
    public HashMap<String, Object> idCheckAjax(String email) {
        System.out.println("con : " + email);

        return userService.userEmailOverlap(email);
    }
    @RequestMapping("/join")
    public String join(UserJoinDTO userJoinDTO) {
        System.out.println("join con userJoinDTO :" + userJoinDTO.toString());
        userService.join(userJoinDTO);
        return "redirect:/user/preJoinForm";
    }

    @RequestMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }
}
