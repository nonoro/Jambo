package jambo.service;

import jambo.domain.Authority;
import jambo.domain.TechStack;
import jambo.domain.admin.Admin;
import jambo.domain.board.Report;
import jambo.domain.user.Point;
import jambo.domain.user.User;
import jambo.dto.AdminJoinDTO;
import jambo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AuthorityRepository authorityRepository;

    private final UserRepository userRepository;

    private final NormalBoardRepository normalBoardRepository;

    private final StudyBoardRepository studyBoardRepository;

    private final ReportRepository reportRepository;

    LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0,0,0));
    LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));


    public HashMap<String, Object> adminEmailOverlap(String email) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", adminRepository.existsByEmail(email));
        return map;
    }

    @Transactional
    public void join(AdminJoinDTO adminJoinDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        adminJoinDTO.setPassword(passwordEncoder.encode(adminJoinDTO.getPassword()));

        Admin admin = adminJoinDTO.toEntity();
        User adminToUser = adminJoinDTO.toUser();


        adminRepository.save(admin);
        authorityRepository.save(new Authority(admin.getEmail(), "ROLE_USER"));
        authorityRepository.save(new Authority(admin.getEmail(), "ROLE_ADMIN"));

        adminToUser.setPoint(new Point(0,0));
        userRepository.save(adminToUser);

    }

    /**
     * ??? ????????? ??????
     * */
    public int countUsers(){
        return userRepository.countBy();
    }

    /**
     * ?????? ????????? ?????? ??? ??????
     * */
    public int countUsersByJoinToday(){
        return userRepository.countByJoinDateBetween(startDatetime, endDatetime);
    }


    /**
     * ?????? ????????? ??? ??????
     * */
    public int countNormalBoard(){
        return normalBoardRepository.countBy();
    }
    /**
     * ?????? ????????? ????????? ??????
     * */
    public int countNormalBoardByToday(){
        return normalBoardRepository.countByWriteDateBetween(startDatetime, endDatetime);
    }


    /**
     * ????????? ?????? ????????? ??? ??????
     * */
    public int countStudyBoard(){
        return studyBoardRepository.countBy();
    }

    /**
     * ?????? ????????? ????????? ?????? ????????? ??? ??????
     * */
    public int countStudyBoardByToday(){
        return studyBoardRepository.countByWriteDateBetween(startDatetime, endDatetime);
    }

    /**
     * ??? ????????? ????????? ???
     * */
    public int countReportedBoards(){
        return reportRepository.countBy();
    }
    /**
     * ?????? ????????? ????????? ???
     * */
    public int countReportedBoardsByToday(){
        return reportRepository.countByReportDateBetween(startDatetime, endDatetime);
    }

    /**
     * ?????? ?????? ?????????
     * */
    public Page<Report> findAll(Pageable page){
        return reportRepository.findAllByOrderByReportDateDesc(page);
    }

    /**
     * ?????? ?????? ??????
     * */
    public Page<User> findAllUser(Pageable pageable){
        return userRepository.findAllByOrderByJoinDateDesc(pageable);
    }
}
