package kr.hhplus.be.server.api.reservation;

import io.swagger.v3.oas.annotations.Operation;
import kr.hhplus.be.server.annotation.AuthorizationHeader;
import kr.hhplus.be.server.api.reservation.dto.ReservationRequest;
import kr.hhplus.be.server.api.reservation.dto.ReservationResponse;
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
public class ReservationController {

    private final ReservationFacade reservationFacade;
    private final PaymentFacade paymentFacade;

    @AuthorizationHeader
    @Operation(summary = "좌석 예약 요청", description = "콘서트와 좌석을 선택하여 예약합니다.")
    @PostMapping("/concert/seats/temp/reservation")
    public ResponseEntity<TempReservationResponse> tempReservationSeat(
            @RequestBody TempReservationRequest request
    ){
        TempReservationDto tempReservationDto = reservationFacade.reserveTempSeat(
                request.getUserId(), request.getSeatId(), request.getUserId(), request.getTokenId());
        return ResponseEntity.ok(TempReservationResponse.of(tempReservationDto.tempReservationId(),tempReservationDto.expiresAt()));
    }

    @AuthorizationHeader
    @Operation(summary = "결제 요청", description = "예약에 대한 결제를 진행합니다.")
    @PostMapping("/reservation/payment")
    public ResponseEntity<ReservationResponse> reservationSeat(@RequestBody ReservationRequest request){
        ReservationDto reservationDto = paymentFacade.completeReservation(request.getUserId(),
                request.getConcertScheduleId(), request.getSeatId()
                , request.getTokenId(), request.getTemporaryReservationId(), request.getPaymentData());
        return ResponseEntity.ok(ReservationResponse.of(reservationDto.paymentId(), reservationDto.reservationId(), reservationDto.seatId()));
    }

}
