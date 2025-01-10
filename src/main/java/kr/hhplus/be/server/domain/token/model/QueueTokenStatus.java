package kr.hhplus.be.server.domain.token.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QueueTokenStatus {

    PENDING("대기중"),
    ACTIVE("사용가능"),
    EXPIRED("만료");

    private final String text;

}


