package kr.hhplus.be.server.infrastructure.token;

import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.domain.token.model.QueueToken;
import kr.hhplus.be.server.domain.token.model.QueueTokenStatus;
import kr.hhplus.be.server.domain.token.repository.QueueTokenReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QueueTokenReaderImpl implements QueueTokenReader {

    private final QueueTokenJpaRepository queueTokenJpaRepository;

    @Override
    public List<QueueToken> findTopNOrderByEnqueuedAt(QueueTokenStatus queueTokenStatus, int count) {

        return queueTokenJpaRepository.findTop10ByStatusOrderByEnqueuedAtAsc(queueTokenStatus);

    }

    @Override
    public Optional<QueueToken> tokenFindById(String token) {
        return queueTokenJpaRepository.findById(token);
    }

    @Override
    public Optional<QueueToken> findByUserAndConcert(Long userId, Long concertId) {
        return queueTokenJpaRepository.findByUserAndConcertLock(userId,concertId);
    }


}
