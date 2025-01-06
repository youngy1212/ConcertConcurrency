package kr.hhplus.be.server.domain.token;

import kr.hhplus.be.server.domain.concert.Concert;
import kr.hhplus.be.server.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueTokenService {

    private final QueueTokenStore queueTokenStore;

    public QueueToken issueToken(final User user, final Concert concert) {

        QueueToken queueToken = QueueToken.create(user, concert);
        queueTokenStore.save(queueToken);
        return queueToken;
    }

}
