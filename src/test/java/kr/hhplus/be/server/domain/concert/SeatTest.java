package kr.hhplus.be.server.domain.concert;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SeatTest {

    @DisplayName("AVAILABLE 이외에 임시예약 전환시 실패")
    @Test
    void testReserveFail() {
        // given
        Concert concert = Concert.create("공연", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,18,0));
        Seat seat = Seat.create(20,SeatStatus.BOOKED,10000L,concertSchedule);


        // when   // then
        assertThatThrownBy(seat::reserve)
                .isInstanceOf(CustomException.class)
                .hasMessage("이미 선택된 좌석입니다.");


        assertEquals(SeatStatus.BOOKED, seat.getStatus());
    }

    @DisplayName("임시예약 성공")
    @Test
    void testReserve() {
        // given
        Concert concert = Concert.create("공연", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,18,0));
        Seat seat = Seat.create(20,SeatStatus.AVAILABLE,10000L,concertSchedule);

        // when
        seat.reserve();

        // then
        assertEquals(SeatStatus.RESERVED, seat.getStatus());
    }


    @DisplayName("임시예약 상태가 아니면 확정 예약 실패")
    @Test
    void testBookFail() {
        // given
        Concert concert = Concert.create("공연", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,18,0));
        Seat seat = Seat.create(20,SeatStatus.AVAILABLE,10000L,concertSchedule);

        // when

        // when   // then
        assertThatThrownBy(seat::book)
                .isInstanceOf(CustomException.class)
                .hasMessage("좌석이 임시 예약된 상태가 아닙니다.");

        // then
        assertEquals(SeatStatus.AVAILABLE, seat.getStatus());
    }

    @DisplayName("확정 예약 성공")
    @Test
    void testBook() {
        // given
        Concert concert = Concert.create("공연", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,18,0));
        Seat seat = Seat.create(20,SeatStatus.RESERVED,10000L,concertSchedule);

        // when
        seat.book();

        // then
        assertEquals(SeatStatus.BOOKED, seat.getStatus());
    }



    @DisplayName("예약 상태가 아니면 예약 취소 실패")
    @Test
    void testCancelReserveFail() {
        // given
        Concert concert = Concert.create("공연", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024,12,12,18,0));
        Seat seat = Seat.create(20,SeatStatus.BOOKED,10000L,concertSchedule);

        // when

        // when   // then
        assertThatThrownBy(seat::cancelReserve)
                .isInstanceOf(CustomException.class)
                .hasMessage("좌석이 예약된 상태가 아닙니다.");

        // then
        assertEquals(SeatStatus.BOOKED, seat.getStatus());
    }

}