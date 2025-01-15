package kr.hhplus.be.server.domain.concert.repository;

import java.util.Optional;
import kr.hhplus.be.server.domain.concert.model.Seat;

public interface ConcertCommand {

    Optional<Seat> findByIdLock(long seatId);
}
