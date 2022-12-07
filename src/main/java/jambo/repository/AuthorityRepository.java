package jambo.repository;

import jambo.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    List<Authority> findByEmail(String id);

}
