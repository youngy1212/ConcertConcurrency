package kr.hhplus.be.server.api.reservation;

import kr.hhplus.be.server.annotation.AuthorizationHeader;
import kr.hhplus.be.server.api.reservation.dto.ReservationRequest;
import kr.hhplus.be.server.api.reservation.dto.ReservationResponse;
import kr.hhplus.be.server.api.reservation.dto.SwaggerReservationController;
import kr.hhplus.be.server.api.reservation.dto.TempReservationRequest;
import kr.hhplus.be.server.api.reservation.dto.TempReservationResponse;
import kr.hhplus.be.server.application.PaymentFacade;
import kr.hhplus.be.server.application.ReservationFacade;
import kr.hhplus.be.server.application.dto.ReservationDto;
import kr.hhplus.be.server.application.dto.TempReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController implements SwaggerReservationController {

    private final ReservationFacade reservationFacade;
    private final PaymentFacade paymentFacade;

    @AuthorizationHeader
    @PostMapping("/concert/seats/temp/reservation")
    public ResponseEntity<TempReservationResponse> tempReservationSeat(
            @RequestBody TempReservationRequest request
    ){
        TempReservationDto tempReservationDto = reservationFacade.reserveTempSeat(
                request.getUserId(), request.getSeatId(), request.getUserId(), request.getTokenId());
        return ResponseEntity.ok(TempReservationResponse.of(tempReservationDto.tempReservationId(),tempReservationDto.expiresAt()));
    }

    @AuthorizationHeader
    @PostMapping("/reservation/payment")
    public ResponseEntity<ReservationResponse> reservationSeat(@RequestBody ReservationRequest request){
        ReservationDto reservationDto = paymentFacade.completeReservation(request.getUserId(),
                request.getConcertScheduleId(), request.getSeatId()
                , request.getTokenId(), request.getTemporaryReservationId(), request.getPaymentData());
        return ResponseEntity.ok(ReservationResponse.of(reservationDto.paymentId(), reservationDto.reservationId(), reservationDto.seatId()));
    }

}
