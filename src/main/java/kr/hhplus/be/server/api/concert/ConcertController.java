package kr.hhplus.be.server.api.concert;

import java.util.List;
import kr.hhplus.be.server.annotation.AuthorizationHeader;
import kr.hhplus.be.server.api.concert.dto.ConcertDateResponse;
import kr.hhplus.be.server.api.concert.dto.SeatResponse;
import kr.hhplus.be.server.api.concert.dto.SwaggerConcertController;
import kr.hhplus.be.server.domain.concert.service.ConcertQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConcertController implements SwaggerConcertController {

    private final ConcertQueryService concertQueryService;

    @AuthorizationHeader
    @GetMapping("/concert/date/{concertId}")
    public ResponseEntity<List<ConcertDateResponse>> getConcertDates(
            @PathVariable Long concertId){
        return ResponseEntity.ok(concertQueryService.getAllConcertSchedule(concertId));
    }

    @AuthorizationHeader
    @GetMapping("/concert/seats/{concertScheduleId}")
    public ResponseEntity<SeatResponse> getConcertSeats(
            @PathVariable Long concertScheduleId
    ){
        return ResponseEntity.ok(concertQueryService.getConcertSeats(concertScheduleId));
    }

}
