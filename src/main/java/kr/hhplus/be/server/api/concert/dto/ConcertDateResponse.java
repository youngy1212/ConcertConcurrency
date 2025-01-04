package kr.hhplus.be.server.api.concert.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ConcertDateResponse {

    private final Long concert_id;
    private final LocalDateTime concert_date;

    @Builder
    private ConcertDateResponse(Long concert_id, LocalDateTime concert_date) {
        this.concert_id = concert_id;
        this.concert_date = concert_date;
    }

    public static ConcertDateResponse of(Long concert_id, LocalDateTime concert_date) {
        return ConcertDateResponse.builder()
                .concert_id(concert_id)
                .concert_date(concert_date).build();

    }
}
