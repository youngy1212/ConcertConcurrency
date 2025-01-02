package kr.hhplus.be.server.api.reservation;

import java.time.LocalDateTime;
import kr.hhplus.be.server.api.ApiResponse;
import kr.hhplus.be.server.api.reservation.dto.TempReservationRequest;
import kr.hhplus.be.server.api.reservation.dto.TempReservationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @PostMapping("/concert/seats/temp/reservation")
    public ApiResponse<TempReservationResponse> tempReservationSeat(@RequestBody TempReservationRequest request){
        return ApiResponse.ok(TempReservationResponse.of(1L, LocalDateTime.now()));
    }

}
