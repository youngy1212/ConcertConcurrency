package kr.hhplus.be.server.domain.reservation;

import static kr.hhplus.be.server.domain.concert.model.SeatStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.concert.model.Concert;
import kr.hhplus.be.server.domain.concert.model.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.model.Seat;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;
import kr.hhplus.be.server.domain.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TemporaryReservationTest {

    @DisplayName("좌석 임시예약 시간이 지난 경우")
    @Test
    void TemporayReservationIsExpiredFalse() {
        // given
        User user = User.create("유저이름", "email.com");
        Concert concert = Concert.create("공연", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,18,0));
        Seat seat = Seat.create(10,AVAILABLE,100000L,concertSchedule);
        String queueToken = "QUEUE_TOKEN_ID";
        LocalDateTime time = LocalDateTime.of(2024,12,12,10,0);
        TemporaryReservation temporaryReservation = TemporaryReservation.create(concertSchedule,user,seat,time,queueToken);

        boolean expired = temporaryReservation.isExpired(time);
        // when

        // then
        assertFalse(expired);
    }

    @DisplayName("좌석 임시예약 시간이 지나지 않은 경우")
    @Test
    void TemporayReservationIsExpiredTure() {
        // given
        User user = User.create("유저이름", "email.com");
        Concert concert = Concert.create("공연", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,18,0));
        Seat seat = Seat.create(10,AVAILABLE,100000L,concertSchedule);
        String queueToken = "QUEUE_TOKEN_ID";
        LocalDateTime time = LocalDateTime.of(2024,12,12,10,0);
        TemporaryReservation temporaryReservation = TemporaryReservation.create(concertSchedule,user,seat,time,queueToken);

        boolean expired = temporaryReservation.isExpired(time.plusMinutes(6));
        // when

        // then
        assertTrue(expired);
    }


}