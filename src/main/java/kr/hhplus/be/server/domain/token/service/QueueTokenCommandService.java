package kr.hhplus.be.server.domain.token.service;

import kr.hhplus.be.server.domain.concert.model.Concert;
import kr.hhplus.be.server.domain.token.model.QueueToken;
import kr.hhplus.be.server.domain.token.repository.QueueTokenCommand;
import kr.hhplus.be.server.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueTokenCommandService {

    private final QueueTokenCommand queueTokenCommand;

    public QueueToken issueToken(final User user, final Concert concert) {

        QueueToken queueToken = QueueToken.create(user, concert);
        queueTokenCommand.save(queueToken);
        return queueToken;
    }



}
