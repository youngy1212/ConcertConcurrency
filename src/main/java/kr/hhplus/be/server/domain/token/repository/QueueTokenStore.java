package kr.hhplus.be.server.domain.token.repository;

import kr.hhplus.be.server.domain.token.model.QueueToken;

public interface QueueTokenStore {

    QueueToken save(QueueToken queueToken);
}
