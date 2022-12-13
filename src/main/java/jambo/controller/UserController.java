package jambo.controller;


import jambo.domain.board.NormalBoard;
import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import jambo.dto.UserJoinDTO;
import jambo.dto.UserMyPageResponseDTO;
import jambo.dto.UserResponseDTO;
import jambo.dto.UserUpdateDTO;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
@Transactional
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

        Long id = user.getId();
        User dbUser = userService.myPage(id);
        String dbFile = fileService.getUrlPath();

        List<NormalBoard> normalBoardList = boardService.showNormalBoard(user);
        List<StudyBoard> studyBoardList = studyBoardService.showStudyBoard(user);
        //여기 안에 들어가 있는거 찍어보고 싶은데 어떻게 찍냐

        int nomalBoardListSize = normalBoardList.size();
        int studyBoardListSize = studyBoardList.size();

        UserMyPageResponseDTO responseMyPage = UserMyPageResponseDTO.from(dbUser); //이게 무슨 뜻이었지... ㅇㅅ 히

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

        return "user/myPage";
    }

    //하고 싶은거 : 유저의 닉네임을 누르면 그 닉네임에 맞는 정보가 나옴
    //일단 유저의 닉네임을 가지고 디비로 감 
    //디비에서 닉네임에 맞는 아이디를 가지고 나옴 그 아이디에서 값정보 다 들어있기 떄문에 정보 빼내기 가능

    @RequestMapping("/profile/{id}")
    public String profile(Model model, @PathVariable Long id){
        User dbUser = userService.myPage(id);
        String dbFile = fileService.getUrlPath();
        UserMyPageResponseDTO userMyPageResponseDTO = UserMyPageResponseDTO.from(dbUser);

        model.addAttribute("profile", dbUser);
        model.addAttribute("savePath", dbFile);
        System.out.println("userResponseDTO = " + userMyPageResponseDTO);
        
        return "/user/profile";
    }

    /**
     * 회원정보 수정 폼
     */
    @RequestMapping("/openUpdateForm")
    public String updateForm(@AuthenticationPrincipal User user, Model model) {
        User dbUser = userService.findUser(user);
        model.addAttribute("userInfo", dbUser);

        return "/user/updateMyInfo";
    }

    /**
     * 회원정보 수정 완료
     */
    @RequestMapping("/update")
    public String update(UserJoinDTO userJoinDTO, @AuthenticationPrincipal User user) {
        userService.update(userJoinDTO, user);

        return "redirect:/user/myPage";
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
