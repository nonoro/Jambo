package jambo.repository;

import jambo.domain.user.User;
import jambo.domain.user.UserTechStack;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserTechStackRepository extends JpaRepository<UserTechStack, Long> {
    void deleteByUser(User user);
}
