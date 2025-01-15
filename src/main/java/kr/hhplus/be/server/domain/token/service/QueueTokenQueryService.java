package kr.hhplus.be.server.domain.token.service;

import java.util.Optional;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.token.model.QueueToken;
import kr.hhplus.be.server.domain.token.repository.QueueTokenQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueTokenQueryService {

    private final QueueTokenQuery queueTokenQuery;

    public void authenticateToken(String token) {

        QueueToken queueToken =  queueTokenQuery.tokenFindById(token).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,"잘못된 경로로 접근하였습니다."));

        if(queueToken.isExpired()) {
            throw new CustomException("대기시간이 만료되었습니다.");
        }

    }

    public Optional<QueueToken> findToken(Long userId, Long concertId) {
        return queueTokenQuery.findToken(userId, concertId);
    }
}
