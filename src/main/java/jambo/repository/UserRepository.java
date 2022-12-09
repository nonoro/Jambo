package jambo.repository;

import jambo.domain.user.User;
import jambo.domain.user.type.MBTI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByMbti(MBTI mbti);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    /**
     * 해당 유저(Long id)의 모든 정보 조회
     */
    User findUserById(Long id);
}
