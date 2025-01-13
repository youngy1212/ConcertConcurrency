package kr.hhplus.be.server.api.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ReservationResponse {

    @Schema( description = "결제 ID")
    private final Long paymentTd;

    @Schema( description = "예약 ID")
    private final Long reservationTd;

    @Schema( description = "예약 ID")
    private final Long seatId;


    private ReservationResponse(Long paymentTd, Long reservationTd, Long seatId) {
        this.paymentTd = paymentTd;
        this.reservationTd = reservationTd;
        this.seatId = seatId;
    }

    public static ReservationResponse of(Long paymentTd, Long reservationTd, Long seatId) {
      return new ReservationResponse(paymentTd, reservationTd,seatId );
    }
}
