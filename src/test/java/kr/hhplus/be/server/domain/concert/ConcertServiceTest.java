package kr.hhplus.be.server.domain.concert;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;
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
        Long concertId = 1L;

        //  when && then
        when(concertReader.findById(concertId)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> concertService.getConcertById(concertId))
                .isInstanceOf(CustomException.class)
                .hasMessage("콘서트를 찾을 수 없습니다.");

    }


}