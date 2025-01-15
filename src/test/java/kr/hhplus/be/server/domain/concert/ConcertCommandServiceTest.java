package kr.hhplus.be.server.domain.concert;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import kr.hhplus.be.server.domain.concert.model.Concert;
import kr.hhplus.be.server.domain.concert.model.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.model.Seat;
import kr.hhplus.be.server.domain.concert.model.SeatStatus;
import kr.hhplus.be.server.domain.concert.repository.ConcertCommand;
import kr.hhplus.be.server.domain.concert.service.ConcertCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConcertCommandServiceTest {


    @Mock
    ConcertCommand concertCommand;

    @InjectMocks
    ConcertCommandService concertCommandService;

    @DisplayName("콘서트 좌석을 찾아 Seat 반환")
    @Test
    void seatSuccess() {
        // given
        long seatId = 3L;
        Concert concert = Concert.create("콘서트", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024, 12, 12, 8, 50));
        Seat seat = Seat.create(20, SeatStatus.AVAILABLE, 2000L, concertSchedule);
        when(concertCommand.findByIdLock(seatId)).thenReturn(Optional.empty());

        // when // then
        assertThatThrownBy(()-> concertCommandService.findByIdLock(seatId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("콘서트의 좌석을 찾을 수 없습니다.");

    }


}