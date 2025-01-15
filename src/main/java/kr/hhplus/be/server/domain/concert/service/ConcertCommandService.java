package kr.hhplus.be.server.domain.concert.service;

import java.util.NoSuchElementException;
import kr.hhplus.be.server.domain.concert.model.Seat;
import kr.hhplus.be.server.domain.concert.repository.ConcertCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertCommandService {

    private final ConcertCommand concertCommand;

    public Seat findByIdLock(final long seatId) {
        return concertCommand.findByIdLock(seatId).orElseThrow(() -> new NoSuchElementException("콘서트의 좌석을 찾을 수 없습니다."));
    }

}
