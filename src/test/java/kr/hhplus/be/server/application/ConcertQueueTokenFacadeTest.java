package kr.hhplus.be.server.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.concert.Concert;
import kr.hhplus.be.server.domain.token.QueueToken;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.infrastructure.concert.ConcertJpaRepository;
import kr.hhplus.be.server.infrastructure.user.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@Transactional
class ConcertQueueTokenFacadeTest {

    @Autowired
    private UserJpaRepository UserJpaRepository;

    @Autowired
    private ConcertJpaRepository ConcertJpaRepository;

    @Autowired
    private ConcertQueueTokenFacade concertQueueTokenFacade;

    @DisplayName("토큰 정상 발급 확인")
    @Test
    void testIssueQueueToken() {
        // given
        User save = UserJpaRepository.save(User.create("유저", "eamil@naemver"));
        Concert SaveConcert = ConcertJpaRepository.save(Concert.create("콘서트1","인스파이어"));

        // when
        QueueToken queueToken = concertQueueTokenFacade.issueQueueToken(save.getId(), SaveConcert.getId());

        // then
        assertThat(queueToken).isNotNull();
        assertThat(queueToken.getUser()).isEqualTo(save);
        assertThat(queueToken.getConcert()).isEqualTo(SaveConcert);
    }

    @DisplayName("비정상 공연 토큰 발급 요청")
    @Test
    public void testIssueQueueTokenConcertNotFound() {
        // given
        long concertId = 2L;
        User save = UserJpaRepository.save(User.create("유저", "eamil@naemver"));

        // when //then
        assertThatThrownBy(()-> concertQueueTokenFacade.issueQueueToken(save.getId(),concertId))
                .isInstanceOf(CustomException.class)
                .hasMessage("콘서트를 찾을 수 없습니다.");

    }

    @DisplayName("비정상 유저 토큰 발급 요청")
    @Test
    public void testIssueQueueTokenUserNotFound() {
        // given
        long userId = 1L;
        Concert SaveConcert = ConcertJpaRepository.save(Concert.create("콘서트1","인스파이어"));

        // when //then
        assertThatThrownBy(()-> concertQueueTokenFacade.issueQueueToken(userId,SaveConcert.getId()))
                .isInstanceOf(CustomException.class)
                .hasMessage("유저를 찾을 수 없습니다.");

    }


}