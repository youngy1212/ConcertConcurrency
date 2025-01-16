package kr.hhplus.be.server.api.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TempReservationResponse {

    @Schema(description = "임시 예약 ID")
    private Long tempReservationId;

    @Schema(description = "임시 예약 만료 시간")
    private LocalDateTime expiresAt;

    private TempReservationResponse(Long tempReservationId, LocalDateTime expiresAt) {
        this.tempReservationId = tempReservationId;
        this.expiresAt = expiresAt;
    }

    public static TempReservationResponse of(Long tempReservationId, LocalDateTime expiresAt) {
        return new TempReservationResponse(tempReservationId, expiresAt);
    }
}
