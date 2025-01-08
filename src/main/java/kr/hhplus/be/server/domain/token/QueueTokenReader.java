package kr.hhplus.be.server.domain.token;

import java.util.List;

public interface QueueTokenReader {

    List<QueueToken> findTopNOrderByEnqueuedAt(QueueTokenStatus queueTokenStatus, int count);


}