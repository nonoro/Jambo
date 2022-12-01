package jambo.repository;

import jambo.domain.user.User;
import jambo.domain.user.type.MBTI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByMbti(MBTI mbti);


    /**
     * 아이디 중복체크
     */
    boolean existsByEmail(String email);

}
