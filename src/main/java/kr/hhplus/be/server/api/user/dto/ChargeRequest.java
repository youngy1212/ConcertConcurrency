package kr.hhplus.be.server.api.user.dto;

import lombok.Getter;

@Getter
public class ChargeRequest {
    private Long userId;
    private Long amount;
}
