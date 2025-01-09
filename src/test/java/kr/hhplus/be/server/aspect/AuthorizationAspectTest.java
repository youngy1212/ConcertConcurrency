package kr.hhplus.be.server.aspect;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.token.QueueTokenService;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

class AuthorizationAspectTest {

    @InjectMocks
    private QueueTokenService queueTokenService;

    @Mock
    private AuthorizationAspect authorizationAspect;

    @DisplayName("잘못된 인증정보로 예외 발생")
    @Test
    void testAuthorization_Exception() {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        JoinPoint joinPoint = mock(JoinPoint.class);

        ServletRequestAttributes attributes = mock(ServletRequestAttributes.class);
        when(attributes.getRequest()).thenReturn(request);
        RequestContextHolder.setRequestAttributes(attributes);

        // when
        when(request.getHeader("Authorization")).thenReturn(null);
        when(request.getHeader("Authorization")).thenReturn(null);

        // then

        // when // then
        assertThatThrownBy(()-> authorizationAspect.Authorization(joinPoint))
                .isInstanceOf(CustomException.class)
                .hasMessage("잘못 된 경로입니다.");

    }

}