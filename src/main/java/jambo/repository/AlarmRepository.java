package jambo.repository;

import jambo.domain.Alarm;
import jambo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByUserAndIsReadOrderByReceivedTimeDesc(User user, boolean isRead);
}
