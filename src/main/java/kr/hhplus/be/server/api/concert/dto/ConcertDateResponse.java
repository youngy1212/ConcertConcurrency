package kr.hhplus.be.server.api.concert.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.concert.ConcertSchedule;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ConcertDateResponse {

    @Schema( description = "콘서트 ID")
    private final Long concert_id;

    @Schema(description = "콘서트 날짜")
    private final LocalDateTime concert_date;

    @Builder
    private ConcertDateResponse(Long concert_id, LocalDateTime concert_date) {
        this.concert_id = concert_id;
        this.concert_date = concert_date;
    }

    public static ConcertDateResponse of(ConcertSchedule concertSchedule) {
        return ConcertDateResponse.builder()
                .concert_id(concertSchedule.getId())
                .concert_date(concertSchedule.getConcertDate()).build();

    }
}
