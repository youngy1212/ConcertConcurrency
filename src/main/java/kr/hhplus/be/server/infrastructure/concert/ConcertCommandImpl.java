package kr.hhplus.be.server.infrastructure.concert;

import java.util.Optional;
import kr.hhplus.be.server.domain.concert.model.Seat;
import kr.hhplus.be.server.domain.concert.repository.ConcertCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertCommandImpl implements ConcertCommand {

    private final SeatJpaRepository seatJpaRepository;

    @Override
    public Optional<Seat> findByIdLock(long seatId) {
        return seatJpaRepository.findByIdLock(seatId);
    }
}
