package kr.hhplus.be.server.api.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ReservationResponse {

    @Schema( description = "결제 ID")
    private final Long paymentTd;
    @Schema( description = "예약 ID")
    private final Long reservationTd;


    private ReservationResponse(Long paymentTd, Long reservationTd) {
        this.paymentTd = paymentTd;
        this.reservationTd = reservationTd;
    }

    public static ReservationResponse of(Long paymentTd, Long reservationTd) {
      return new ReservationResponse(paymentTd, reservationTd);
    }
}
