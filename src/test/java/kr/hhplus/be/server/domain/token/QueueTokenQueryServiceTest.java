package kr.hhplus.be.server.domain.token;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.token.repository.QueueTokenQuery;
import kr.hhplus.be.server.domain.token.service.QueueTokenQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QueueTokenQueryServiceTest {

    @Mock
    private QueueTokenQuery queueTokenQuery;

    @InjectMocks
    private QueueTokenQueryService queueTokenQueryService;


    @DisplayName("인증할 토큰이 존재하지 않는 경우 오류 반환")
    @Test
    void authenticateTokenTokenNotFound() {
        // given
        String queueToken = "QUEUE_TOKEN_ID";

        when(queueTokenQuery.tokenFindById(queueToken)).thenReturn(Optional.empty());

        // when // then
        assertThatThrownBy(()-> queueTokenQueryService.authenticateToken(queueToken))
                .isInstanceOf(CustomException.class)
                .hasMessage("잘못된 경로로 접근하였습니다.");

    }




}