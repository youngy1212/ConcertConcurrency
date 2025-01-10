package kr.hhplus.be.server.domain.reservation;

import static kr.hhplus.be.server.domain.concert.model.SeatStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.concert.model.Concert;
import kr.hhplus.be.server.domain.concert.model.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.model.Seat;
import kr.hhplus.be.server.domain.reservation.model.Reservation;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;
import kr.hhplus.be.server.domain.reservation.repository.ReservationStore;
import kr.hhplus.be.server.domain.reservation.service.ReservationService;
import kr.hhplus.be.server.domain.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService;

    @Mock
    private ReservationStore reservationStore;

    @DisplayName("임시예약을 생성합니다.")
    @Test
    void CreateTemporaryReservation() {
        // given
        User user = User.create("유저이름", "email.com");
        Concert concert = Concert.create("공연", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,18,0));
        Seat seat = Seat.create(10,AVAILABLE,100000L,concertSchedule);
        String queueToken = "QUEUE_TOKEN_ID";
        LocalDateTime expiresAt = LocalDateTime.of(2024,12,12,18,0).plusMinutes(5);

        TemporaryReservation temporaryReservation = TemporaryReservation.create(concertSchedule, user, seat, expiresAt,
                queueToken);

        when(reservationStore.temporaryReservationSave(any(TemporaryReservation.class))).thenReturn(temporaryReservation);

        // when
        TemporaryReservation result = reservationService.createTemporaryReservation(concertSchedule,
                user, seat, queueToken);

        // then
        assertEquals(result.getUser().getName(), user.getName());
        assertEquals(result.getConcertSchedule().getConcertDate(), concertSchedule.getConcertDate());
        assertEquals(result.getSeat().getSeatNumber(), seat.getSeatNumber());
        assertEquals(result.getQueueTokenId(), queueToken);
        verify(reservationStore).temporaryReservationSave(any(TemporaryReservation.class));

    }

    @DisplayName("예약을 생성합니다.")
    @Test
    void CreateReservation() {
        // given
        User user = User.create("유저이름", "email.com");
        Concert concert = Concert.create("공연", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,18,0));
        Seat seat = Seat.create(10,AVAILABLE,100000L,concertSchedule);
        String queueToken = "QUEUE_TOKEN_ID";
        LocalDateTime expiresAt = LocalDateTime.of(2024,12,12,18,0).plusMinutes(5);

        Reservation reservation = Reservation.create(concertSchedule, user, seat);

        when(reservationStore.reservationSave(any(Reservation.class))).thenReturn(reservation);

        // when
        Reservation result = reservationService.createReservation(concertSchedule, user, seat);

        // then
        assertEquals(result.getUser().getName(), user.getName());
        assertEquals(result.getConcertSchedule().getConcertDate(), concertSchedule.getConcertDate());
        assertEquals(result.getSeat().getSeatNumber(), seat.getSeatNumber());
        verify(reservationStore).reservationSave(any(Reservation.class));

    }



}