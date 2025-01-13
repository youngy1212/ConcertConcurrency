package kr.hhplus.be.server.application;

import kr.hhplus.be.server.application.dto.ReservationDto;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.concert.model.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.model.Seat;
import kr.hhplus.be.server.domain.concert.service.ConcertService;
import kr.hhplus.be.server.domain.payment.model.Payment;
import kr.hhplus.be.server.domain.payment.model.PaymentStatus;
import kr.hhplus.be.server.domain.payment.service.PaymentService;
import kr.hhplus.be.server.domain.reservation.model.Reservation;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;
import kr.hhplus.be.server.domain.reservation.service.ReservationService;
import kr.hhplus.be.server.domain.user.model.User;
import kr.hhplus.be.server.domain.user.service.UserService;
import kr.hhplus.be.server.infrastructure.gateway.PaySystem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentFacade {


    //좌석 예약
    private final ReservationService reservationService;
    private final ConcertService concertService;
    private final UserService userService;
    private final PaymentService paymentService;

    //예약 완료
    @Transactional
    public ReservationDto completeReservation(Long userId, Long ConcertScheduleId, Long seatId, String tokenId, Long temporaryReservationId, String paymentData) {

        User user = userService.getUserById(userId);
        TemporaryReservation temporaryReservation = reservationService.getTemporaryReservation(temporaryReservationId);
        ConcertSchedule concertSchedule = concertService.getConcertSchedule(ConcertScheduleId);
        Seat seat = concertService.findByIdLock(seatId);

        if(!seat.getSeatId().equals(temporaryReservation.getSeat().getSeatId())){
            throw new CustomException(HttpStatus.CONFLICT, "예약 정보가 일치하지 않습니다.");
        }

        if(!user.getId().equals(temporaryReservation.getUser().getId())){
            throw new CustomException(HttpStatus.CONFLICT, "예약 정보가 일치하지 않습니다.");
        }

        boolean pay = PaySystem.pay(seat.getPrice());

        if(!pay){  //결제 실패시
            throw new CustomException(HttpStatus.PAYMENT_REQUIRED, "결제에 실패하였습니다.");
        }

        //결제 성공시
        seat.book();
        Reservation reservation = reservationService.createReservation(concertSchedule, user, seat);
        Payment payment = paymentService.savePayment(user, reservation, seat.getPrice(), PaymentStatus.SUCCESS);
        return new ReservationDto(payment.getId(),reservation.getId(), reservation.getSeat().getSeatId());


    }
}
