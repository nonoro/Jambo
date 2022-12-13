package jambo.service;



import jambo.domain.Alarm;
import jambo.domain.Authority;
import jambo.domain.TechStack;
import jambo.domain.user.Point;
import jambo.domain.user.User;
import jambo.dto.UserJoinDTO;
import jambo.repository.AlarmRepository;
import jambo.repository.AuthorityRepository;
import jambo.repository.TechStackRepository;
import jambo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRep;

    private final TechStackRepository techStackRepository;

    private final AuthorityRepository authorityRepository;

    private final AlarmRepository alarmRepository;


    public HashMap<String, Object> userEmailOverlap(String email) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", userRep.existsByEmail(email));
        return map;
    }

    @Transactional
    public Long join(UserJoinDTO userJoinDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userJoinDTO.setPassword(passwordEncoder.encode(userJoinDTO.getPassword()));
        List<String> userTechStacks = userJoinDTO.getUserTechStacks();
        User user = userJoinDTO.toEntity();
        user.setPoint(new Point(0,0));
        if(userTechStacks!=null) {
            List<TechStack> techStacks = techStackRepository.findAllByTechStackNameIn(userTechStacks);
            user.setTechStacks(techStacks);
        }
        authorityRepository.save(new Authority(user.getEmail(), "ROLE_USER"));

        return userRep.save(user).getId();
    }

    public User findUser(User user){
        return userRep.findById(user.getId()).get();
    }

    public User myPage(Long id) {
        return userRep.findUserById(id);
    }

    public List<Alarm> findAlarm(User user) {
        List<Alarm> byUser = alarmRepository.findByUserAndIsReadOrderByReceivedTimeDesc(user, false);
        return byUser;
    }
}
