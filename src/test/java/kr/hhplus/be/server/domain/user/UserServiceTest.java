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

}