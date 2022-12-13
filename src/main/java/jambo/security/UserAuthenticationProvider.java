package jambo.security;

import jambo.domain.Authority;
import jambo.domain.admin.Admin;
import jambo.domain.user.User;
import jambo.repository.AdminRepository;
import jambo.repository.AuthorityRepository;
import jambo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    private final AdminRepository adminRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        Admin admin = null;
        String id = auth.getName();
        User user = userRepository.findByEmail(id).orElse(null);
        System.out.println(user);

        if (user != null) {
            String password = (String) auth.getCredentials();
            System.out.println("user = " + user.getPassword());
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("패스워드 오류입니다.");
            }
        } else {
            admin = adminRepository.findByEmail(id).orElseThrow(() -> new UsernameNotFoundException(id + "는 없는 회원입니다."));
            String password = (String) auth.getCredentials();
            System.out.println("admin = " + admin.getPassword());
            if (!passwordEncoder.matches(password, admin.getPassword())) {
                throw new BadCredentialsException("패스워드 오류입니다.");
            }
        }

        List<Authority> list = authorityRepository.findByEmail(id);

        if (list.isEmpty()) {
            throw new UsernameNotFoundException(id + "는 아무 권한이 없습니다.");
        }

        List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
        for (Authority authority : list) {
            authList.add(new SimpleGrantedAuthority(authority.getRole()));
        }
        System.out.println("나갑니다!!");

        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, null, authList);
        } else {
            return new UsernamePasswordAuthenticationToken(admin, null, authList);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}






