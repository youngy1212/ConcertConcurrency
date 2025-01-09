package kr.hhplus.be.server.api.reservation.dto;

import lombok.Getter;

@Getter
public class ReservationRequest {
    private Long userId;
    private Long seatId;
    private String tokenId;
    private Long temporaryReservationId;
    private Long concertScheduleId;
    private String paymentData;
}

