package kr.hhplus.be.server.infrastructure.token;

import jakarta.persistence.LockModeType;
import java.util.List;
import kr.hhplus.be.server.domain.token.QueueToken;
import kr.hhplus.be.server.domain.token.QueueTokenStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface QueueTokenJpaRepository extends JpaRepository<QueueToken, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<QueueToken> findTop10ByStatusOrderByEnqueuedAtAsc(QueueTokenStatus status);


}
