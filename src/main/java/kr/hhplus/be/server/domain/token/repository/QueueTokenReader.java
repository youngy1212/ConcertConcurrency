package kr.hhplus.be.server.domain.token.repository;

import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.domain.token.model.QueueToken;
import kr.hhplus.be.server.domain.token.model.QueueTokenStatus;

public interface QueueTokenReader {

    List<QueueToken> findTopNOrderByEnqueuedAt(QueueTokenStatus queueTokenStatus, int count);

    Optional<QueueToken> tokenFindById(String token);

    Optional<QueueToken> findToken(Long user, Long concert);
}