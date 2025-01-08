package kr.hhplus.be.server.infrastructure.token;

import java.util.List;
import kr.hhplus.be.server.domain.token.QueueToken;
import kr.hhplus.be.server.domain.token.QueueTokenReader;
import kr.hhplus.be.server.domain.token.QueueTokenStatus;
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


}
