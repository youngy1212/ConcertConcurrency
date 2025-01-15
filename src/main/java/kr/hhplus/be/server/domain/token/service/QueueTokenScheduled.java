package kr.hhplus.be.server.domain.token.service;


import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import kr.hhplus.be.server.domain.token.model.QueueToken;
import kr.hhplus.be.server.domain.token.model.QueueTokenStatus;
import kr.hhplus.be.server.domain.token.repository.QueueTokenCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueTokenScheduled {

    private final QueueTokenCommand queueTokenCommand;

    @Transactional
    //@Scheduled(fixedDelay = 1000) //1초 간격으로 실행
    public void activeTokens(){

        List<QueueToken> tokens = queueTokenCommand.findTopNOrderByEnqueuedAt(QueueTokenStatus.PENDING, 10);

        for(QueueToken token : tokens){
            token.tokenActive(LocalDateTime.now());
        }
    }

}
