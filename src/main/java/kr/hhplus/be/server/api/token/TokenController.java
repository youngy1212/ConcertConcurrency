package kr.hhplus.be.server.api.token;

import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDateTime;
import kr.hhplus.be.server.api.token.dto.TokenRequest;
import kr.hhplus.be.server.api.token.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Operation(summary = "대기열 토큰 발급", description = "유저의 대기열 토큰을 발급합니다.")
    @PostMapping("/tokens")
    public ResponseEntity<TokenResponse> Servicetoken(@RequestBody TokenRequest request){
        return ResponseEntity.ok(TokenResponse.of("uuid", LocalDateTime.now()));
    }

}
