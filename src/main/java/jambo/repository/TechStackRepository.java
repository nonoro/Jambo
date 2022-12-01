package jambo.repository;

import jambo.domain.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TechStackRepository extends JpaRepository<TechStack, Long> {
    List<TechStack> findAllByTechStackNameIn(Collection<String> techStackNames);
}
