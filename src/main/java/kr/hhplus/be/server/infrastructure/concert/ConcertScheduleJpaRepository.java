package kr.hhplus.be.server.infrastructure.concert;

import java.util.List;
import kr.hhplus.be.server.domain.concert.ConcertSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertScheduleJpaRepository extends JpaRepository<ConcertSchedule, Long> {

    List<ConcertSchedule> findAllByConcertId(long concertId);

}
