package kr.hhplus.be.server.domain.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.concert.Concert;
import kr.hhplus.be.server.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QueueTokenServiceTest {

    @Mock
    private QueueTokenStore queueTokenStore;

    @InjectMocks
    private QueueTokenService queueTokenService;

    @DisplayName("유저가 QueueToken에 들어와, 대기중 토큰을 발급한다.")
    @Test
    void issueToken_ShouldReturnQueueToken() {
        // given
        User user = User.create("유저이름", "email.com");
        Concert concert = Concert.create("공연", LocalDateTime.now());
        QueueToken queueToken = QueueToken.create(user,concert);

        when(queueTokenStore.save(any(QueueToken.class))).thenReturn(queueToken);

        // when
        QueueToken result = queueTokenService.issueToken(user, concert);

        // then
        assertEquals(user.getName(), result.getUser().getName());
        assertEquals(concert.getTitle(), result.getConcert().getTitle());
        assertEquals(result.getStatus(), QueueTokenStatus.PENDING);
        verify(queueTokenStore).save(any(QueueToken.class));

    }






}