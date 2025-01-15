package kr.hhplus.be.server.domain.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.user.repository.UserCommand;
import kr.hhplus.be.server.domain.user.service.UserCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserCommandServiceTest {

    @Mock
    UserCommand userCommand;

    @InjectMocks
    UserCommandService userCommandService;

    @DisplayName("잘못된 금액으로 충전에 실패합니다.")
    @Test
    void ChargePointInvalidAmount() {
        // given
        Long userId = 1L;
        Long invalidAmount = 0L;

        // when // then
        assertThatThrownBy(()-> userCommandService.chargePoint(userId, invalidAmount  ))
                .isInstanceOf(CustomException.class)
                .hasMessage("잘못 청구되었습니다.");

    }

    @DisplayName("충전할 포인트를 찾을 수 없습니다.")
    @Test
    void ChargePointNotFound() {
        // given
        Long userId = 1L;

        // when
        when(userCommand.charge(userId)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(()-> userCommandService.chargePoint(userId,1000L  ))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("포인트를 찾을 수 없습니다.");

    }

}