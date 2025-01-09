package kr.hhplus.be.server.domain.token;

import java.util.List;
import java.util.Optional;

public interface QueueTokenReader {

    List<QueueToken> findTopNOrderByEnqueuedAt(QueueTokenStatus queueTokenStatus, int count);

    Optional<QueueToken> tokenFindById(String token);
}