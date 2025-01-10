package kr.hhplus.be.server.aspect;

import jakarta.servlet.http.HttpServletRequest;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.token.service.QueueTokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthorizationAspect {

    @Autowired
    private QueueTokenService queueTokenService; // 토큰 검증을 위한 서비스


    @Pointcut("@annotation(kr.hhplus.be.server.annotation.AuthorizationHeader)")
    public void pointcut() {
    }

    @Before("pointcut()") //이전에 실행
    public void Authorization(JoinPoint joinPoint) {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String token = request.getHeader("QUEUE-TOKEN");

        if (token == null || token.isEmpty()) {
            throw new CustomException("잘못 된 경로입니다.");
        }

        queueTokenService.authenticateToken(token); //인증

    }


}
