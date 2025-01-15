package kr.hhplus.be.server.domain.reservation.service;


import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.concert.model.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.model.Seat;
import kr.hhplus.be.server.domain.reservation.model.Reservation;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;
import kr.hhplus.be.server.domain.reservation.repository.ReservationCommand;
import kr.hhplus.be.server.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCommandService {

    private final ReservationCommand reservationCommand;


    public TemporaryReservation createTemporaryReservation(ConcertSchedule concertSchedule, User user, Seat seat, String queueTokenId) {

        LocalDateTime time = LocalDateTime.now().plusMinutes(10); //좌석은 10분간 유효
        // 임시 예약 생성
        TemporaryReservation tempReservation = TemporaryReservation.create(concertSchedule, user, seat, time,queueTokenId);

        return reservationCommand.temporaryReservationSave(tempReservation);
    }


    //실제 예약 생성
    public Reservation createReservation(ConcertSchedule concertSchedule, User user, Seat seat) {

        //예약 생성
        Reservation reservation = Reservation.create(concertSchedule,user,seat);

        return reservationCommand.reservationSave(reservation);

    }


}
