package kr.hhplus.be.server.api.token;

import io.swagger.v3.oas.annotations.Operation;
import kr.hhplus.be.server.api.token.dto.TokenResponse;
import kr.hhplus.be.server.application.ConcertQueueTokenFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final ConcertQueueTokenFacade concertQueueTokenFacade;

    @Operation(summary = "대기열 토큰 발급", description = "유저의 대기열 토큰을 발급합니다.")
    @GetMapping("/tokens/{userId}/{concertId}")
    public ResponseEntity<TokenResponse> issueServiceToken(
            @PathVariable Long userId,
            @PathVariable Long concertId
    ){
        return ResponseEntity.ok(concertQueueTokenFacade.issueQueueToken(userId, concertId));
    }

}
