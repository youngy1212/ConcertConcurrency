package kr.hhplus.be.server.infrastructure.concert;

import java.util.List;
import kr.hhplus.be.server.domain.concert.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeatJpaRepository extends JpaRepository<Seat, Long> {

    @Query("SELECT s.seatId FROM Seat s where s.concertSchedule.id = :concertScheduleId")
    List<Long> findByConcertScheduleId(@Param("concertScheduleId")long concertScheduleId);
}
