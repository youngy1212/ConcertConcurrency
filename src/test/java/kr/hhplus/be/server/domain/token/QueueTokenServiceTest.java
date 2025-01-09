package kr.hhplus.be.server.domain.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import kr.hhplus.be.server.domain.common.exception.CustomException;
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

    @Mock
    private QueueTokenReader queueTokenReader;

    @InjectMocks
    private QueueTokenService queueTokenService;

    @DisplayName("유저가 QueueToken에 들어와, 대기중 토큰을 발급한다.")
    @Test
    void issueToken_ShouldReturnQueueToken() {
        // given
        User user = User.create("유저이름", "email.com");
        Concert concert = Concert.create("공연", "고척돔");
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

    @DisplayName("인증할 토큰이 존재하지 않는 경우 오류 반환")
    @Test
    void authenticateTokenTokenNotFound() {
        // given
        String queueToken = "QUEUE_TOKEN_ID";

        when(queueTokenReader.tokenFindById(queueToken)).thenReturn(Optional.empty());

        // when // then
        assertThatThrownBy(()-> queueTokenService.authenticateToken(queueToken))
                .isInstanceOf(CustomException.class)
                .hasMessage("잘못된 경로로 접근하였습니다.");

    }




}