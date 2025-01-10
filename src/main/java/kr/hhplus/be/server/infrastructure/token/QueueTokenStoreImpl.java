package kr.hhplus.be.server.infrastructure.token;

import kr.hhplus.be.server.domain.token.model.QueueToken;
import kr.hhplus.be.server.domain.token.repository.QueueTokenStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QueueTokenStoreImpl implements QueueTokenStore {

    private final QueueTokenJpaRepository queueTokenJpaRepository;

    @Override
    public QueueToken save(QueueToken queueToken) {
        return queueTokenJpaRepository.save(queueToken);
    }
}
