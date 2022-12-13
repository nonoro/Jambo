package jambo.repository;

import jambo.domain.TechStack;
import jambo.domain.user.User;
import jambo.domain.user.UserTechStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserTechStackRepository extends JpaRepository<UserTechStack, Long> {
    void deleteByUser(User user);
}
