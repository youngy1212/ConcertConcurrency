package kr.hhplus.be.server.api.concert.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Getter
public class SeatResponse {

    @Schema(description = "좌석 ID 리스트")
    private List<Long> seat_ids;

    private SeatResponse(List<Long> seat_ids) {
       this.seat_ids = seat_ids;
    }

    public static SeatResponse of(List<Long> seat_ids) {
        return new SeatResponse(seat_ids);
    }
}
