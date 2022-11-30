package jambo.mvc.repository;

import jambo.mvc.domain.user.IconShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IconShopRepository extends JpaRepository<IconShop, Long> {
}
