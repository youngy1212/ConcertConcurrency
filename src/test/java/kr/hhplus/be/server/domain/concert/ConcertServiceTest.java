package kr.hhplus.be.server.domain.concert;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.api.concert.dto.ConcertDateResponse;
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
        when(concertReader.findByConcertId(concertId)).thenReturn(Collections.emptyList());

        // when // then
        assertThatThrownBy(()-> concertService.getConcertSchedule(concertId))
                .isInstanceOf(CustomException.class)
                .hasMessage("콘서트의 예약 가능한 날을 찾을 수 없습니다.");

    }

    @DisplayName("콘서트 스케줄을 찾아서 List<ConcertDateResponse>로 반환")
    @Test
    void test() {
        // given
        long concertId = 1L;
        Concert concert = Concert.create("콘서트", "고척돔");
        ConcertSchedule concertSchedule = ConcertSchedule.create(concert, LocalDateTime.of(2024, 12, 12, 8, 50));
        ConcertSchedule concertSchedule2 = ConcertSchedule.create(concert,LocalDateTime.of(2024,12,13,8,50));

        List<ConcertSchedule> schedules = List.of(concertSchedule, concertSchedule2);
        when(concertReader.findByConcertId(concertId)).thenReturn(schedules);

        // when
        List<ConcertDateResponse> result = concertService.getConcertSchedule(concertId);

        // then
        assertThat(result.get(0).getConcert_date())
                .isEqualTo(LocalDateTime.of(2024, 12, 12, 8, 50));
        assertThat(result.get(1).getConcert_date())
                .isEqualTo(LocalDateTime.of(2024, 12, 13, 8, 50));


    }


}