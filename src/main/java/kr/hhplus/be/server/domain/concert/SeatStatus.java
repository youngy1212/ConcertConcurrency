package kr.hhplus.be.server.domain.concert;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SeatStatus {

    AVAILABLE("예약가능"),
    RESERVED("임시예약중"),
    PENDING("예약 대기"),
    BOOKED("예약완료");

    private final String text;
}
