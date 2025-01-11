package kr.hhplus.be.server.api.reservation.dto;

import lombok.Getter;

@Getter
public class TempReservationRequest {
    private Long seatId;
    private Long userId;
    private Long ConcertScheduleId;
    private String TokenId;
}
