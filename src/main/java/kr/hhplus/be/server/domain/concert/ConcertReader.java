package kr.hhplus.be.server.domain.concert;

import java.util.List;
import java.util.Optional;

public interface ConcertReader {

    Optional<Concert> findById(final long id);

    List<ConcertSchedule> findByConcertId(long concertId);
}
