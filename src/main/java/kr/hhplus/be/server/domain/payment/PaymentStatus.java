package kr.hhplus.be.server.domain.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {

    SUCCESS("성공"),
    FAILURE("실패");

    private final String text;
}
