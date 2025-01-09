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

    public TemporaryReservation createTemporaryReservation(ConcertSchedule concertSchedule, User user, Seat seat, String queueTokenId) {

        LocalDateTime time = LocalDateTime.now().plusMinutes(10); //좌석은 10분간 유효
        // 임시 예약 생성
        TemporaryReservation tempReservation = TemporaryReservation.create(concertSchedule, user, seat, time,queueTokenId);

        return reservationStore.temporaryReservationSave(tempReservation);
    }


    //실제 예약 생성
    public Reservation createReservation(ConcertSchedule concertSchedule, User user, Seat seat) {

        //예약 생성
        Reservation reservation = Reservation.create(concertSchedule,user,seat);

        return reservationStore.reservationSave(reservation);

    }
}
