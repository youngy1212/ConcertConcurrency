package kr.hhplus.be.server.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import kr.hhplus.be.server.domain.concert.model.Concert;
import kr.hhplus.be.server.domain.concert.model.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.model.Seat;
import kr.hhplus.be.server.domain.concert.model.SeatStatus;
import kr.hhplus.be.server.domain.reservation.model.Reservation;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;
import kr.hhplus.be.server.domain.user.model.User;
import kr.hhplus.be.server.infrastructure.concert.ConcertJpaRepository;
import kr.hhplus.be.server.infrastructure.concert.ConcertScheduleJpaRepository;
import kr.hhplus.be.server.infrastructure.concert.SeatJpaRepository;
import kr.hhplus.be.server.infrastructure.reservation.ReservationJpaRepository;
import kr.hhplus.be.server.infrastructure.reservation.TemporaryReservationJpaRepository;
import kr.hhplus.be.server.infrastructure.user.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class PaymentFacadeTest {

    @Autowired
    private UserJpaRepository UserJpaRepository;

    @Autowired
    private ConcertJpaRepository concertJpaRepository;

    @Autowired
    private ConcertScheduleJpaRepository concertScheduleJpaRepository;

    @Autowired
    private SeatJpaRepository seatJpaRepository;

    @Autowired
    private TemporaryReservationJpaRepository temporaryReservationJpaRepository;

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;


    @Autowired
    private PaymentFacade paymentFacade;


    @DisplayName("동시에 여러 요청이 와도 하나의 예약만 성공한다.")
    @Test
    public void testConcurrentReservations() throws InterruptedException {
        // given
        User user1 = UserJpaRepository.save(User.create("유저1", "이이메일"));
        User user2 = UserJpaRepository.save(User.create("유저2", "22메일"));
        User user3 = UserJpaRepository.save(User.create("유저3", "222메일"));

        Concert concert = concertJpaRepository.save(Concert.create("콘서트", "올림픽홀"));

        ConcertSchedule concertSchedule = concertScheduleJpaRepository.save(ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,10,0)));
        Seat seat = seatJpaRepository.save(Seat.create(20, SeatStatus.RESERVED , 2000L, concertSchedule));

        //만약 임시로 세명 들어왔다해도
        TemporaryReservation tempReservation = temporaryReservationJpaRepository.save(
                TemporaryReservation.create(concertSchedule, user1, seat, LocalDateTime.now().plusMinutes(5),
                        "token12"));
        TemporaryReservation tempReservation2 = temporaryReservationJpaRepository.save(
                TemporaryReservation.create(concertSchedule, user2, seat, LocalDateTime.now().plusMinutes(5),
                        "token11"));
        TemporaryReservation tempReservation3 = temporaryReservationJpaRepository.save(
                TemporaryReservation.create(concertSchedule, user3, seat, LocalDateTime.now().plusMinutes(5),
                        "token13"));


        int numberOfThreads = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        AtomicInteger successCount = new AtomicInteger(); // 성공 횟수
        AtomicInteger failureCount = new AtomicInteger(); // 실패 횟수

        // when
        executorService.submit(() -> {
            try {
                paymentFacade.completeReservation(
                        user1.getId(),
                        concertSchedule.getId(),
                        seat.getSeatId(),
                        "token1",
                        tempReservation.getId(),
                        "paymentData1"
                );
                successCount.incrementAndGet();
            } catch (Exception e) {
                failureCount.incrementAndGet();
            } finally {
                latch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                paymentFacade.completeReservation(
                        user2.getId(),
                        concertSchedule.getId(),
                        seat.getSeatId(),
                        "token2",
                        tempReservation2.getId(),
                        "paymentData2"
                );
                successCount.incrementAndGet();
            } catch (Exception e) {
                failureCount.incrementAndGet();
            } finally {
                latch.countDown();
            }
        });

        executorService.submit(() -> {
            try {
                paymentFacade.completeReservation(user3.getId(), concertSchedule.getId(), seat.getSeatId(), "token3", tempReservation3.getId(), "paymentData3"
                );
                successCount.incrementAndGet();
            } catch (Exception e) {
                failureCount.incrementAndGet();
            } finally {
                latch.countDown();
            }
        });

        latch.await();
        executorService.shutdown();


        List<Reservation> reservations = reservationJpaRepository.findAll();
        assertThat(reservations.size()).isEqualTo(1);
        assertThat(successCount.get()).isEqualTo(1);
        assertThat(failureCount.get()).isEqualTo(2);

    }

}