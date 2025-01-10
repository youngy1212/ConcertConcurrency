package kr.hhplus.be.server.infrastructure.concert;

import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.domain.concert.model.ConcertSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertScheduleJpaRepository extends JpaRepository<ConcertSchedule, Long> {

    List<ConcertSchedule> findAllByConcertId(long concertId);

    Optional<ConcertSchedule> findById(long concertScheduleId);

}
