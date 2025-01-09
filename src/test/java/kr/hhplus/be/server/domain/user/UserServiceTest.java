package kr.hhplus.be.server.domain.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserReader userReader;

    @InjectMocks
    UserService userService;

    @DisplayName("유저 정보가 없어서 오류 발생")
    @Test
    void userNotFound() {
        // given
        Long userId = 1L;

        //  when && then
        when(userReader.findById(userId)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> userService.getUserById(userId))
                .isInstanceOf(CustomException.class)
                .hasMessage("유저를 찾을 수 없습니다.");

    }

    @DisplayName("잘못된 금액으로 충전에 실패합니다.")
    @Test
    void ChargePointInvalidAmount() {
        // given
        Long userId = 1L;
        Long invalidAmount = 0L;

        // when // then
        assertThatThrownBy(()-> userService.chargePoint(userId, invalidAmount  ))
                .isInstanceOf(CustomException.class)
                .hasMessage("잘못 청구되었습니다.");

    }

    @DisplayName("충전할 포인트를 찾을 수 없습니다.")
    @Test
    void ChargePointNotFound() {
        // given
        Long userId = 1L;

        // when
        when(userReader.findByPoint(userId)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(()-> userService.chargePoint(userId,1000L  ))
                .isInstanceOf(CustomException.class)
                .hasMessage("포인트를 찾을 수 없습니다.");

    }


    @DisplayName("포인트 조회시 포인트 데이터가 없어서 실패")
    @Test
    void getPointNotFound() {
        // given
        Long userId = 1L;

        // when
        when(userReader.findByPoint(userId)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(()-> userService.getPoint(userId ))
                .isInstanceOf(CustomException.class)
                .hasMessage("포인트를 찾을 수 없습니다.");

    }

}