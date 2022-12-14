package jambo.controller;


import jambo.domain.Alarm;
import jambo.domain.board.NormalBoard;
import jambo.domain.board.StudyBoard;
import jambo.domain.user.User;
import jambo.dto.AlarmListResponseDTO;
import jambo.dto.UserJoinDTO;
import jambo.dto.UserMyPageResponseDTO;
import jambo.service.BoardService;
import jambo.service.FileService;
import jambo.service.StudyBoardService;
import jambo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
@Transactional
public class UserController {

    private static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;

    private final UserService userService;

    private final FileService fileService;

    private final BoardService boardService;

    private final StudyBoardService studyBoardService;


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

    @RequestMapping("/profile/{id}")
    public String profile(Model model, @PathVariable Long id){
        User dbUser = userService.myPage(id);
        String dbFile = fileService.getUrlPath();
        UserMyPageResponseDTO userMyPageResponseDTO = UserMyPageResponseDTO.from(dbUser);

        model.addAttribute("profile", dbUser);
        model.addAttribute("savePath", dbFile);
        
        return "user/profile";
    }

    /**
     * 회원정보 수정 폼 열기
     */
    @RequestMapping("/openUpdateForm")
    public String updateForm(@AuthenticationPrincipal User user, Model model) {
        User dbUser = userService.findUser(user);
        model.addAttribute("userInfo", dbUser);

        return "user/updateMyInfo";
    }

    /**
     * 회원정보 수정 DB 수정
     */
    @RequestMapping("/update")
    public String update(UserJoinDTO userJoinDTO, @AuthenticationPrincipal User user) {
        userService.update(userJoinDTO, user);
        return "redirect:/user/myPage";
    }

    @RequestMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    @RequestMapping("/alarm")
    @ResponseBody
    public List<AlarmListResponseDTO> findAlarm(@AuthenticationPrincipal User user) {
        List<Alarm> alarm = userService.findAlarm(user);
        List<AlarmListResponseDTO> alarmResponses = Alarm.toAlarmListResponseDTOS(alarm);
        return alarmResponses;
    }
}
