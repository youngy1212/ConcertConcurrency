package kr.hhplus.be.server.api.user;


import io.swagger.v3.oas.annotations.Operation;
import kr.hhplus.be.server.api.user.dto.ChargeRequest;
import kr.hhplus.be.server.api.user.dto.ChargeResponse;
import kr.hhplus.be.server.api.user.dto.PointResponse;
import kr.hhplus.be.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointController {

    private final UserService userService;

    @Operation(summary = "잔액 충전", description = "유저의 POINT를 충전합니다.")
    @PostMapping("/point/charge")
    public ResponseEntity<ChargeResponse> tempReservationSeat(@RequestBody ChargeRequest request){
        return ResponseEntity.ok(userService.chargePoint(request.getUserId(),
                request.getAmount()));
    }

    @Operation(summary = "잔액 조회", description = "유저의 POINT를 조회합니다.")
    @GetMapping("/point/{userId}")
    public ResponseEntity<PointResponse> tempReservationSeat(
            @PathVariable Long userId
    ){
        return ResponseEntity.ok(userService.getPoint(userId));
    }

}
