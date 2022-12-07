package jambo.repository;

import jambo.domain.user.User;
import jambo.domain.user.type.MBTI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByMbti(MBTI mbti);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    /**
     * 해당 유저(email)의 모든 정보 조회
     */
    User findUserByEmail(String email);
}
