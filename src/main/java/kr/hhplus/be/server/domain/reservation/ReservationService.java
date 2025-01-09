package kr.hhplus.be.server.domain.reservation;


import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.concert.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationStore reservationStore;

    public TemporaryReservation createTemporaryReservation(ConcertSchedule concertSchedule, User user, Seat seat,LocalDateTime expiresAt, String queueTokenId) {

        // 임시 예약 생성
        TemporaryReservation tempReservation = TemporaryReservation.create(concertSchedule, user, seat, expiresAt, queueTokenId);

        return reservationStore.save(tempReservation);
    }
}
