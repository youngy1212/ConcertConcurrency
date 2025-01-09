package kr.hhplus.be.server.application;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.api.token.dto.TokenResponse;
import kr.hhplus.be.server.domain.concert.Concert;
import kr.hhplus.be.server.domain.concert.ConcertService;
import kr.hhplus.be.server.domain.token.QueueToken;
import kr.hhplus.be.server.domain.token.QueueTokenService;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertQueueTokenFacade {

    private final UserService userService;
    private final ConcertService concertService;
    private final QueueTokenService queueTokenService;

    //토큰 생성
    @Transactional
    public TokenResponse issueQueueToken(long userId, long concertId) {

        User user = userService.getUserById(userId);
        Concert concert = concertService.getConcertById(concertId);
        QueueToken queueToken = queueTokenService.issueToken(user, concert);

        return TokenResponse.of(queueToken.getQueueTokenId(),queueToken.getExpiresAt());
    }



}
