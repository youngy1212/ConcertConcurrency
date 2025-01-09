package kr.hhplus.be.server.domain.concert;

import java.util.List;
import java.util.Optional;

public interface ConcertReader {

    Optional<Concert> findById(final long id);

    List<ConcertSchedule> findAllByConcertId(long concertId);

    List<Long> findByConcertScheduleId(long concertScheduleId);

    Optional<Seat> findBySeatId(long seatId);

    Optional<ConcertSchedule> getConcertSchedule(long concertScheduleId);
}
