package jambo.controller;


import jambo.domain.TechStack;
import jambo.domain.user.User;
import jambo.domain.user.UserTechStack;
import jambo.dto.UserJoinDTO;
import jambo.service.FileService;
import jambo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

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

//    public ModelAndView myPage(String userEmail){
//        userEmail = "test@test.com";
//        System.out.println("userEmail = " + userEmail);
//        User dbUser = userService.myPage(userEmail);
//
//        return new ModelAndView("user/myPage", "myPage", dbUser);
    @RequestMapping("/myPage")
    public String myPage(Model model){
        String userEmail = "test@test.com";
        List<String> userRequestTechStacks = List.of("C", "Java", "Python");
        User dbMyPage = userService.myPage(userEmail);
        String dbFile = fileService.getUrlPath();

        model.addAttribute("TechStacks", userRequestTechStacks);
        model.addAttribute("myPage", dbMyPage);
        model.addAttribute("savePath", dbFile);

        return "user/myPage";
    }
}
