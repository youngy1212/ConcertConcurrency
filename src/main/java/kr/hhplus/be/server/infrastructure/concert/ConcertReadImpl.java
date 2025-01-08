package kr.hhplus.be.server.infrastructure.concert;

import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.domain.concert.Concert;
import kr.hhplus.be.server.domain.concert.ConcertReader;
import kr.hhplus.be.server.domain.concert.ConcertSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertReadImpl implements ConcertReader {

    private final ConcertJpaRepository concertJpaRepository;
    private final ConcertScheduleJpaRepository concertScheduleJpaRepository;
    private final SeatJpaRepository seatJpaRepository;

    @Override
    public Optional<Concert> findById(final long id) {
        return concertJpaRepository.findById(id);
    }

    @Override
    public List<ConcertSchedule> findByConcertId(long concertId) {
        return concertScheduleJpaRepository.findAllByConcertId(concertId);
    }

    @Override
    public List<Long> findByConcertScheduleId(long concertScheduleId) {
        return seatJpaRepository.findByConcertScheduleId(concertScheduleId);
    }
}
