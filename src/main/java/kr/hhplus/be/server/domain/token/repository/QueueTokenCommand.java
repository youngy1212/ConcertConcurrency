package kr.hhplus.be.server.domain.token.repository;

import java.util.List;
import kr.hhplus.be.server.domain.token.model.QueueToken;
import kr.hhplus.be.server.domain.token.model.QueueTokenStatus;

public interface QueueTokenCommand {

    QueueToken save(QueueToken queueToken);

    List<QueueToken> findTopNOrderByEnqueuedAt(QueueTokenStatus queueTokenStatus, int count);
}
