package kr.hhplus.be.server.api.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public class TempReservationResponse {

    @Schema(description = "임시 예약 ID")
    private Long temp_reservation_id;

    @Schema(description = "임시 예약 만료 시간")
    private LocalDateTime expires_at;

    private TempReservationResponse(Long temp_reservation_id, LocalDateTime expires_at) {
        this.temp_reservation_id = temp_reservation_id;
        this.expires_at = expires_at;
    }

    public static TempReservationResponse of(Long temp_reservation_id, LocalDateTime expires_at) {
        return new TempReservationResponse(temp_reservation_id, expires_at);
    }
}
