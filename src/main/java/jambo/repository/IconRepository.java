package jambo.repository;

import jambo.domain.user.Icon;
import jambo.domain.user.IconShop;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IconRepository extends JpaRepository<Icon, Long> {
    Optional<Icon> findByUserAndIconShop(User user, IconShop iconShop);

    List<Icon> findAllByUser(User user);
}
