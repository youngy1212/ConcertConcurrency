package kr.hhplus.be.server.api.reservation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TempReservationRequest {

    private Long seatId;
    private Long userId;
    private Long concertScheduleId;
    private String tokenId;

    public TempReservationRequest(Long seatId, Long userId, Long concertScheduleId, String tokenId) {
        this.seatId = seatId;
        this.userId = userId;
        this.concertScheduleId = concertScheduleId;
        this.tokenId = tokenId;
    }
}
