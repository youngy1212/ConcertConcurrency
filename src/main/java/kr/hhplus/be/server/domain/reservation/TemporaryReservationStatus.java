package kr.hhplus.be.server.domain.reservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TemporaryReservationStatus {

    RESERVED("예약중"),
    BOOKED("예약완료"),
    EXPIRED("만료");

    private final String text;

}
