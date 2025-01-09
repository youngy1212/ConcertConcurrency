package kr.hhplus.be.server.api.concert;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import kr.hhplus.be.server.api.concert.dto.ConcertDateResponse;
import kr.hhplus.be.server.api.concert.dto.SeatRequest;
import kr.hhplus.be.server.api.concert.dto.SeatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConcertController {

    @Operation(summary = "예약 가능 날짜 조회", description = "예약 가능한 날짜를 조회합니다.")
    @GetMapping("/concert/date/{concertId}")
    public ResponseEntity<List<ConcertDateResponse>> getConcertDates(@PathVariable Long concertId){
        return ResponseEntity.ok(List.of());
    }

    @Operation(summary = "예약 가능 좌석 조회", description = "예약 가능한 좌석을 조회합니다.")
    @PostMapping("/concert/seats")
    public ResponseEntity<SeatResponse> getConcertSeats(@RequestBody SeatRequest request){
        return ResponseEntity.ok(SeatResponse.of(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)));
    }

}
