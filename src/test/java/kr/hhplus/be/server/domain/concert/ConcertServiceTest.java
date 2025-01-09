package kr.hhplus.be.server.domain.concert;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.api.concert.dto.ConcertDateResponse;
import kr.hhplus.be.server.api.concert.dto.SeatResponse;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {

    @Mock
    ConcertReader concertReader;

    @InjectMocks
    ConcertService concertService;

    @DisplayName("공연 정보가 없어서 오류 발생")
    @Test
    void concertNotFound() {
        // given
        long concertId = 1L;

        //  when && then
        when(concertReader.findById(concertId)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> concertService.getConcertById(concertId))
                .isInstanceOf(CustomException.class)
                .hasMessage("콘서트를 찾을 수 없습니다.");

    }

    @DisplayName("콘서트 스케줄을 찾을 수 없습니다.")
    @Test
    void ConcertScheduleNotFound() {
        // given
        long concertId = 1L;
        when(concertReader.findAllByConcertId(concertId)).thenReturn(Collections.emptyList());

        // when // then
        assertThatThrownBy(()-> concertService.getAllConcertSchedule(concertId))
                .isInstanceOf(CustomException.class)
                .hasMessage("콘서트의 예약 가능한 날을 찾을 수 없습니다.");

    }

    @DisplayName("콘서트 스케줄을 찾아서 List<ConcertDateResponse>로 반환")
    @Test
    void ConcertScheduleSuccess() {
        // given
        long concertId = 1L;
        Concert concert = Concert.create("콘서트", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024, 12, 12, 8, 50));
        ConcertSchedule concertSchedule2 = ConcertSchedule.create(concert,LocalDateTime.of(2024,12,13,8,50));

        List<ConcertSchedule> schedules = List.of(concertSchedule, concertSchedule2);
        when(concertReader.findAllByConcertId(concertId)).thenReturn(schedules);

        // when
        List<ConcertDateResponse> result = concertService.getAllConcertSchedule(concertId);

        // then
        assertThat(result.get(0).getConcertDate())
                .isEqualTo(LocalDateTime.of(2024, 12, 12, 8, 50));
        assertThat(result.get(1).getConcertDate())
                .isEqualTo(LocalDateTime.of(2024, 12, 13, 8, 50));


    }

    @DisplayName("concertScheduleId로 콘서트 좌석을 찾을 수 없습니다.")
    @Test
    void ConcertSeatNotFound() {
        // given
        long concertScheduleId = 3L;
        when(concertReader.findByConcertScheduleId(concertScheduleId)).thenReturn(Collections.emptyList());

        // when // then
        assertThatThrownBy(()-> concertService.getConcertSeats(concertScheduleId))
                .isInstanceOf(CustomException.class)
                .hasMessage("콘서트의 좌석을 찾을 수 없습니다.");

    }

    @DisplayName("콘서트 좌석을 찾아 List<seat> 반환")
    @Test
    void ConcertSeatSuccess() {
        // given
        long concertScheduleId = 3L;
        List<Long> seatIds = List.of(1L, 2L);
        when(concertReader.findByConcertScheduleId(concertScheduleId)).thenReturn(seatIds);

        // when
        SeatResponse seatResponse = concertService.getConcertSeats(concertScheduleId);
        //then
        assertThat(seatResponse.getSeat_ids()).hasSize(2);
        assertThat(seatResponse.getSeat_ids()).containsExactly(1L, 2L);

    }


    @DisplayName("콘서트 좌석을 찾아 Seat 반환")
    @Test
    void seatSuccess() {
        // given
        long seatId = 3L;
        Concert concert = Concert.create("콘서트", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024, 12, 12, 8, 50));
        Seat seat = Seat.create(20, SeatStatus.AVAILABLE, 2000L, concertSchedule);
        when(concertReader.findByIdLock(seatId)).thenReturn(Optional.empty());

        // when // then
        assertThatThrownBy(()-> concertService.findByIdLock(seatId))
                .isInstanceOf(CustomException.class)
                .hasMessage("콘서트의 좌석을 찾을 수 없습니다.");

    }


}