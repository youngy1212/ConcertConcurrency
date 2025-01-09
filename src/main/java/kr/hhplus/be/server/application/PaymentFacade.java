package kr.hhplus.be.server.application;

import kr.hhplus.be.server.api.reservation.dto.ReservationResponse;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.concert.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.ConcertService;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.payment.Payment;
import kr.hhplus.be.server.domain.payment.PaymentService;
import kr.hhplus.be.server.domain.payment.PaymentStatus;
import kr.hhplus.be.server.domain.reservation.Reservation;
import kr.hhplus.be.server.domain.reservation.ReservationService;
import kr.hhplus.be.server.domain.reservation.TemporaryReservation;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.user.UserService;
import kr.hhplus.be.server.infrastructure.gateway.PaySystem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentFacade {


    //좌석 예약
    private final ReservationService reservationService;
    private final ConcertService concertService;
    private final UserService userService;
    private final PaymentService paymentService;

    //좌석을 PENDING 상태로 변경
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setSeatPending(Seat seat) {
        seat.pending();
    }

    //좌석의 PENDING 상태를 취소
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void cancelSeatPending(Seat seat) {
        seat.cancelReserve();
    }

    //예약 완료
    @Transactional
    public ReservationResponse completeReservation(Long userId, Long ConcertScheduleId, Long seatId, String tokenId, Long temporaryReservationId, String paymentData) {

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

        //결제 성공시
        if(pay){
            seat.book();
            Reservation reservation = reservationService.createReservation(concertSchedule, user, seat);
            Payment payment = paymentService.savePayment(user, reservation, seat.getPrice(), PaymentStatus.SUCCESS);
            return ReservationResponse.of(payment.getId(),reservation.getId());
        }else{
            //결제 실패시
            throw new CustomException(HttpStatus.PAYMENT_REQUIRED, "결제에 실패하였습니다.");
        }

    }
}
