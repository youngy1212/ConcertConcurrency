package kr.hhplus.be.server.domain.token.repository;

import java.util.Optional;
import kr.hhplus.be.server.domain.token.model.QueueToken;

public interface QueueTokenQuery {

    Optional<QueueToken> tokenFindById(String token);

    Optional<QueueToken> findToken(Long user, Long concert);
}