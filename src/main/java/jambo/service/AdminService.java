package jambo.service;

import jambo.domain.Authority;
import jambo.domain.admin.Admin;
import jambo.dto.AdminJoinDTO;
import jambo.repository.AdminRepository;
import jambo.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AuthorityRepository authorityRepository;


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

        adminRepository.save(admin);

        authorityRepository.save(new Authority(admin.getEmail(), "ROLE_ADMIN"));
        authorityRepository.save(new Authority(admin.getEmail(), "ROLE_USER"));

    }
}
