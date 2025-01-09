package kr.hhplus.be.server.infrastructure.user;

import java.util.Optional;
import kr.hhplus.be.server.domain.user.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointJpaRepository extends JpaRepository<Point, Long> {
    Optional<Point> findByUserId(Long user_id);
}
