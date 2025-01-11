package kr.hhplus.be.server.api.concert;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import kr.hhplus.be.server.annotation.AuthorizationHeader;
import kr.hhplus.be.server.api.concert.dto.ConcertDateResponse;
import kr.hhplus.be.server.api.concert.dto.SeatResponse;
import kr.hhplus.be.server.domain.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    @AuthorizationHeader
    @Operation(summary = "예약 가능 날짜 조회", description = "예약 가능한 날짜를 조회합니다.")
    @GetMapping("/concert/date/{concertId}")
    public ResponseEntity<List<ConcertDateResponse>> getConcertDates(
            @PathVariable Long concertId){
        return ResponseEntity.ok(concertService.getAllConcertSchedule(concertId));
    }

    @AuthorizationHeader
    @Operation(summary = "예약 가능 좌석 조회", description = "예약 가능한 좌석을 조회합니다.")
    @GetMapping("/concert/seats/{concertScheduleId}")
    public ResponseEntity<SeatResponse> getConcertSeats(
            @PathVariable Long concertScheduleId
    ){
        return ResponseEntity.ok(concertService.getConcertSeats(concertScheduleId));
    }

}
