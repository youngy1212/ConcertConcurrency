package kr.hhplus.be.server.domain.payment;

import static kr.hhplus.be.server.domain.concert.SeatStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.concert.Concert;
import kr.hhplus.be.server.domain.concert.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.reservation.Reservation;
import kr.hhplus.be.server.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentStore paymentStore;

    @InjectMocks
    private PaymentService paymentService;

    @DisplayName("결제 정보를 생성합니다.")
    @Test
    void CreateReservation() {
        // given
        User user = User.create("유저이름", "email.com");
        Concert concert = Concert.create("공연", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,18,0));
        Seat seat = Seat.create(10,AVAILABLE,100000L,concertSchedule);
        Reservation reservation = Reservation.create(concertSchedule, user, seat);

        Payment payment = Payment.create(user, reservation, 10000L, PaymentStatus.SUCCESS);

        when(paymentStore.savePayment(any(Payment.class))).thenReturn(payment);

        // when
        Payment result = paymentService.savePayment(user, reservation,10000L, PaymentStatus.SUCCESS);

        // then
        assertEquals(result.getUser().getName(), user.getName());
        assertEquals(result.getAmount(), 10000L);
        assertEquals(result.getStatus(),  PaymentStatus.SUCCESS);
        verify(paymentStore).savePayment(any(Payment.class));

    }

}