package kr.hhplus.be.server.aspect;

class AuthorizationAspectTest {

//    @Mock
//    private QueueTokenService queueTokenService;
//
//    @InjectMocks
//    private AuthorizationAspect authorizationAspect;
//
//    @DisplayName("잘못된 인증정보로 예외 발생")
//    @Test
//    void testAuthorization_Exception() {
//        // HttpServletRequest를 모킹합니다.
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        when(request.getHeader("Authorization")).thenReturn(null);
//
//        // RequestContextHolder와 ServletRequestAttributes를 모킹합니다.
//        ServletRequestAttributes attributes = mock(ServletRequestAttributes.class);
//        when(attributes.getRequest()).thenReturn(request);
//
//        // RequestContextHolder에 모킹된 attributes를 설정합니다.
//        RequestContextHolder.setRequestAttributes(attributes);
//
//        // JoinPoint를 모킹합니다.
//        JoinPoint joinPoint = mock(JoinPoint.class);
//
//        // 예외가 발생하는지 검증합니다.
//        CustomException exception = assertThrows(CustomException.class, () -> {
//            authorizationAspect.Authorization(joinPoint);
//        });
//
//        assertEquals("잘못 된 경로입니다.", exception.getMessage());
//
//        // RequestContextHolder를 정리합니다.
//        RequestContextHolder.resetRequestAttributes();
//
//    }

}