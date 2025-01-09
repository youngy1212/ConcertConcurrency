package kr.hhplus.be.server.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.concert.Concert;
import kr.hhplus.be.server.domain.concert.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.ConcertService;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.concert.SeatStatus;
import kr.hhplus.be.server.domain.reservation.ReservationService;
import kr.hhplus.be.server.domain.reservation.TemporaryReservation;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.user.UserService;
import kr.hhplus.be.server.infrastructure.concert.ConcertJpaRepository;
import kr.hhplus.be.server.infrastructure.concert.ConcertScheduleJpaRepository;
import kr.hhplus.be.server.infrastructure.concert.SeatJpaRepository;
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
class ReservationFacadeTest {


    @Autowired
    private ReservationFacade reservationFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private ConcertService concertService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserJpaRepository UserJpaRepository;

    @Autowired
    private ConcertJpaRepository ConcertJpaRepository;

    @Autowired
    private ConcertScheduleJpaRepository ConcertScheduleJpaRepository;

    @Autowired
    private SeatJpaRepository seatJpaRepository;

    @DisplayName("임시예약 요청시 잘못된 유저 정보 요청")
    @Test
    void tempReserveSeatNotUser() {

        // given
        User save = UserJpaRepository.save(User.create("유저", "eamil@naemver"));
        Concert Save2 = ConcertJpaRepository.save(Concert.create("콘서트1","인스파이어"));
        ConcertSchedule save3 = ConcertScheduleJpaRepository.save(ConcertSchedule.create(Save2, LocalDateTime.of(2024,12,12,10,00)));
        Seat save4 = seatJpaRepository.save(Seat.create(20, SeatStatus.AVAILABLE, 2000L, save3));
        String tokenId = "TOKEN_ID";
        long userId = 9999L;



        // when //then
        assertThatThrownBy(()-> reservationFacade.tempReserveSeat(userId,save4.getSeatId(),save3.getId(),tokenId))
                .isInstanceOf(CustomException.class)
                .hasMessage("유저를 찾을 수 없습니다.");

    }

    @DisplayName("임시예약 요청시 잘못된 콘서트 정보 요청")
    @Test
    void tempReserveSeatNotConcertSchedule() {

        // given
        User save = UserJpaRepository.save(User.create("유저", "eamil@naemver"));
        Concert Save2 = ConcertJpaRepository.save(Concert.create("콘서트1","인스파이어"));
        ConcertSchedule save3 = ConcertScheduleJpaRepository.save(ConcertSchedule.create(Save2, LocalDateTime.of(2024,12,12,10,00)));
        Seat save4 = seatJpaRepository.save(Seat.create(20, SeatStatus.AVAILABLE, 2000L, save3));
        String tokenId = "TOKEN_ID";
        long ConcertSchedule = 9999L;


        // when //then
        assertThatThrownBy(()-> reservationFacade.tempReserveSeat(save.getId(),save4.getSeatId(),ConcertSchedule ,tokenId))
                .isInstanceOf(CustomException.class)
                .hasMessage("콘서트 일정을 찾을 수 없습니다.");

    }

    @DisplayName("이미 선택한 좌석일 경우")
    @Test
    void tempReserveSeatAlreadyReserved() {

        // given
        User save = UserJpaRepository.save(User.create("유저", "eamil@naemver"));
        Concert Save2 = ConcertJpaRepository.save(Concert.create("콘서트1","인스파이어"));
        ConcertSchedule save3 = ConcertScheduleJpaRepository.save(ConcertSchedule.create(Save2, LocalDateTime.of(2024,12,12,10,00)));
        Seat save4 = seatJpaRepository.save(Seat.create(20, SeatStatus.RESERVED, 2000L, save3));
        String tokenId = "TOKEN_ID";
        long ConcertSchedule = 9999L;


        // when //then
        assertThatThrownBy(()-> reservationFacade.tempReserveSeat(save.getId(),save4.getSeatId(),save3.getId() ,tokenId))
                .isInstanceOf(CustomException.class)
                .hasMessage("이미 선택된 좌석입니다.");

    }

    @DisplayName("좌석을 성공적으로 임시 예약")
    @Test
    void tempReserveSeatSuccess() {

        // given
        User save = UserJpaRepository.save(User.create("유저", "eamil@naemver"));
        Concert Save2 = ConcertJpaRepository.save(Concert.create("콘서트1","인스파이어"));
        ConcertSchedule save3 = ConcertScheduleJpaRepository.save(ConcertSchedule.create(Save2, LocalDateTime.of(2024,12,12,10,00)));
        Seat save4 = seatJpaRepository.save(Seat.create(20, SeatStatus.AVAILABLE, 2000L, save3));
        String tokenId = "TOKEN_ID";

        // when
        TemporaryReservation temporaryReservation = reservationFacade.tempReserveSeat(save.getId(), save4.getSeatId(),
                save3.getId(), tokenId);

        assertNotNull(temporaryReservation);
        assertEquals(save, temporaryReservation.getUser());
        assertEquals(save4, temporaryReservation.getSeat());
        assertEquals(save3, temporaryReservation.getConcertSchedule());

    }


}