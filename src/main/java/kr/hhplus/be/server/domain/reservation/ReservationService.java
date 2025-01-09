package kr.hhplus.be.server.domain.reservation;


import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.concert.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationStore reservationStore;

    private final ReservationReader reservationReader;

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

    public TemporaryReservation getTemporaryReservation(Long reservationId) {
       return reservationReader.getTemporaryReservation(reservationId).orElseThrow(() -> new CustomException(
                HttpStatus.NOT_FOUND,"잘못된 결제 요청입니다."));

    }
}
