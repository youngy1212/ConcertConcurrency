package kr.hhplus.be.server.infrastructure.token;

import java.util.Optional;
import kr.hhplus.be.server.domain.token.model.QueueToken;
import kr.hhplus.be.server.domain.token.repository.QueueTokenQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QueueTokenQueryImpl implements QueueTokenQuery {

    private final QueueTokenJpaRepository queueTokenJpaRepository;

    @Override
    public Optional<QueueToken> tokenFindById(String token) {
        return queueTokenJpaRepository.findById(token);
    }

    @Override
    public Optional<QueueToken> findToken(Long userId, Long concertId) {
        return queueTokenJpaRepository.findByUserAndConcertLock(userId,concertId);
    }


}
