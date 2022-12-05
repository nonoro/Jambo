package jambo.controller;


import jambo.domain.user.User;
import jambo.dto.UserJoinDTO;
import jambo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/preJoinForm")
    public void preJoinForm(){

    }
    @RequestMapping("/joinForm")
    public void join(){

    }

    @RequestMapping("/idcheckAjax")
    @ResponseBody
    public HashMap<String, Object> idCheckAjax(String email) {
        System.out.println("con : "+email);

        return userService.userEmailOverlap(email);
    }

    @RequestMapping("/join")
    public String join(UserJoinDTO userJoinDTO){
        System.out.println("join con userJoinDTO :"+userJoinDTO.toString());
        userService.join(userJoinDTO);
        return "redirect:/user/preJoinForm";
    }

}
