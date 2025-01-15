package kr.hhplus.be.server.domain.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;
import kr.hhplus.be.server.domain.user.repository.UserQuery;
import kr.hhplus.be.server.domain.user.service.UserQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserQueryServiceTest {

    @Mock
    UserQuery userQuery;

    @InjectMocks
    UserQueryService userQueryService;

    @DisplayName("유저 정보가 없어서 오류 발생")
    @Test
    void userNotFound() {
        // given
        Long userId = 1L;

        //  when && then
        when(userQuery.findById(userId)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> userQueryService.getUserById(userId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("유저를 찾을 수 없습니다.");

    }


    @DisplayName("포인트 조회시 포인트 데이터가 없어서 실패")
    @Test
    void getPointNotFound() {
        // given
        Long userId = 1L;

        // when
        when(userQuery.findByPoint(userId)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(()-> userQueryService.getPoint(userId ))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("포인트를 찾을 수 없습니다.");

    }

}