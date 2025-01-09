package kr.hhplus.be.server.api.user;


import io.swagger.v3.oas.annotations.Operation;
import kr.hhplus.be.server.api.user.dto.ChargeRequest;
import kr.hhplus.be.server.api.user.dto.ChargeResponse;
import kr.hhplus.be.server.api.user.dto.PointRequest;
import kr.hhplus.be.server.api.user.dto.PointResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointController {

    @Operation(summary = "잔액 충전", description = "유저의 POINT를 충전합니다.")
    @PostMapping("/point/charge")
    public ResponseEntity<ChargeResponse> tempReservationSeat(@RequestBody ChargeRequest request){
        return ResponseEntity.ok(ChargeResponse.of(1L,1L));
    }

    @Operation(summary = "잔액 조회", description = "유저의 POINT를 조회합니다.")
    @PostMapping("/point")
    public ResponseEntity<PointResponse> tempReservationSeat(@RequestBody PointRequest request){
        return ResponseEntity.ok(PointResponse.of(1L,2L));
    }

}
