package kr.hhplus.be.server.api.token.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TokenResponse {

    @Schema(description = "토큰 UUID")
    private String token_uuid;

    @Schema(description = "토큰 만료 시간")
    private LocalDateTime expires_at;


    private TokenResponse(String token_uuid, LocalDateTime expires_at) {
        this.token_uuid = token_uuid;
        this.expires_at = expires_at;
    }

    public static TokenResponse of(String token_uuid, LocalDateTime expires_at) {
        return new TokenResponse(token_uuid,expires_at);
    }

}
