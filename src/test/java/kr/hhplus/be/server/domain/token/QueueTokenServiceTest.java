package kr.hhplus.be.server.domain.token;

import kr.hhplus.be.server.domain.concert.ConcertReader;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QueueTokenServiceTest {



    @Mock
    private ConcertReader concertReader;

    @Mock
    private QueueTokenStore queueTokenStore;

    @InjectMocks
    private QueueTokenService queueTokenService;

//    @DisplayName("유저가 QueueToken에 들어와, 대기중 토큰을 발급한다.")
//    @Test
//    void issueToken_ShouldReturnQueueToken() {
//        // given
//        String userId = "유저1";
//        User user = User.create("유저이름", "email.com");
//        Concert concert = Concert.create("공연", LocalDateTime.now());
//        QueueToken queueToken = QueueToken.create(user,concert);
//        long concertId = 1L;
//
//        when(userReader.findById(userId)).thenReturn(Optional.of(user));
//        when(concertReader.findById(concertId)).thenReturn(Optional.of(concert));
//        when(queueTokenStore.save(any(QueueToken.class))).thenReturn(queueToken);
//
//        // when
//        QueueToken result = queueTokenService.issueToken(userId, concertId);
//
//        // then
//        assertEquals(user, result.getUser());
//        assertEquals(concert, result.getConcert());
//        verify(userReader).findById(userId);
//        verify(concertReader).findById(concertId);
//        verify(queueTokenStore).save(any(QueueToken.class));
//
//    }






}