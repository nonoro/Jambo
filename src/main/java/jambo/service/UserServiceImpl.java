package jambo.service;


import jambo.domain.TechStack;
import jambo.domain.user.User;
import jambo.dto.UserJoinDTO;
import jambo.repository.TechStackRepository;
import jambo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRep;
    @Autowired
    private TechStackRepository techStackRepository;

    @Override
    public HashMap<String, Object> userEmailOverlap(String email) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", userRep.existsByEmail(email));
        return map;
    }

    @Override
    @Transactional
    public Long join(UserJoinDTO userJoinDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userJoinDTO.setPassword(passwordEncoder.encode(userJoinDTO.getPassword()));
        List<String> userTechStacks = userJoinDTO.getUserTechStacks();
        User user = userJoinDTO.toEntity();
        if(userTechStacks!=null) {
            List<TechStack> techStacks = techStackRepository.findAllByTechStackNameIn(userTechStacks);
            user.setTechStacks(techStacks);
        }

        return userRep.save(user).getId();
    }

    @Override
    public User myPage(String userEmail) {

        return userRep.findUserByEmail(userEmail);
    }

//    @Override
//    public UserTechStack techStack(long id) {

//        return userRep.findById();
//    }
}
