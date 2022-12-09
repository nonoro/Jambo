package jambo.controller;


import jambo.domain.board.NormalBoard;
import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import jambo.dto.UserJoinDTO;
import jambo.dto.UserMyPageResponseDTO;
import jambo.service.BoardService;
import jambo.service.FileService;

import jambo.service.StudyBoardService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private BoardService boardService;

    @Autowired
    private StudyBoardService studyBoardService;

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
        return "index";
    }

    @RequestMapping("/myPage")
    public String myPage(Model model, @AuthenticationPrincipal User user) {
//      String userEmail = "test@test.com";
//      List<String> userRequestTechStacks = List.of("C", "Java", "Python");
//      String dbEmail = user.getEmail();//로그인한 유저의 이메일
//      System.out.println("🤬🤬🤬🤬dbEmail🤬🤬🤬🤬  = " + dbEmail);
//      List<UserTechStack> dbTechStaks = user.getUserTechStacks();
//      System.out.println("🤬🤬⚙️⚙️️dbTechStaks⚙️⚙️🤬🤬🤬  = " + dbTechStaks);
//      User dbMyPage = userService.myPage(dbEmail);//여기에 정보 다 들어있다.

        Long id = user.getId();
        User dbUser = userService.myPage(id);
        String dbFile = fileService.getUrlPath();

        List<NormalBoard> normalBoardList = boardService.showNormalBoard(user);
        List<StudyBoard> studyBoardList = studyBoardService.showStudyBoard(user);
        //여기 안에 들어가 있는거 찍어보고 싶은데 어떻게 찍냐

        int nomalBoardListSize = normalBoardList.size();
        int studyBoardListSize = studyBoardList.size();
        System.out.println("🤬studyBoardListSize🤬 = " + studyBoardListSize);

        UserMyPageResponseDTO responseMyPage = UserMyPageResponseDTO.from(dbUser); //이게 무슨 뜻이었지... ㅇㅅ 히

        System.out.println("🤬🤬⚙️⚙getNickName⚙️⚙️🤬🤬🤬  = " + responseMyPage.getNickName());
        System.out.println("🤬🤬⚙️⚙getName⚙️⚙️🤬🤬🤬  = " + responseMyPage.getName());
        System.out.println("🤬🤬⚙️⚙getEmail⚙️⚙️🤬🤬🤬  = " + responseMyPage.getEmail());
        System.out.println("🤬🤬⚙️⚙getPhone⚙️⚙️🤬🤬🤬  = " + responseMyPage.getPhone());
        System.out.println("🤬🤬⚙️⚙getMbti⚙️⚙️🤬🤬🤬  = " + responseMyPage.getMbti());
        System.out.println("🤬🤬⚙️⚙getUserTechStacks⚙️⚙️🤬🤬🤬  = " + responseMyPage.getUserTechStacks());
        System.out.println("🤬🤬⚙️⚙getLevel⚙️⚙️🤬🤬🤬  = " + responseMyPage.getLevel());
        System.out.println("🤬🤬⚙️⚙getPoint⚙️⚙️🤬🤬🤬  = " + responseMyPage.getPoint());

        for (NormalBoard normalBoard : normalBoardList) {
            log.debug("normalBoard = {}", normalBoard);
        }

        model.addAttribute("myPage", responseMyPage);//여기에 정보 다 들어있다.
        model.addAttribute("savePath", dbFile);
        model.addAttribute("normalBoardList", normalBoardList);
        model.addAttribute("studyBoardList", studyBoardList);
        model.addAttribute("normalBoardListSize", nomalBoardListSize);
        model.addAttribute("studyBoardListSize", studyBoardListSize);
        model.addAttribute("studyBoardList", studyBoardList);

        return "user/MyPage";
    }

    @RequestMapping("/loginForm")
    public String loginForm() {
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
