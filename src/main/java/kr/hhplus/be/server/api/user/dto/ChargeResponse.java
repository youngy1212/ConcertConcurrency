package kr.hhplus.be.server.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ChargeResponse {
    @Schema(description = "유저 UUID")
    private final Long user_id;

    @Schema(example = "10000", description = "충전 금액")
    private final Long amount;

    private ChargeResponse(Long user_id, Long amount) {
        this.user_id = user_id;
        this.amount = amount;
    }

    public static ChargeResponse of(Long user_id, Long amount) {
        return new ChargeResponse(user_id, amount);
    }
}
