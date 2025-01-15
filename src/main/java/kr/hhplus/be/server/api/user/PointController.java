package kr.hhplus.be.server.api.user;


import io.swagger.v3.oas.annotations.Operation;
import kr.hhplus.be.server.api.user.dto.ChargeRequest;
import kr.hhplus.be.server.api.user.dto.ChargeResponse;
import kr.hhplus.be.server.api.user.dto.PointResponse;
import kr.hhplus.be.server.domain.user.service.UserCommandService;
import kr.hhplus.be.server.domain.user.service.UserQueryService;
import kr.hhplus.be.server.domain.user.service.dto.ChargeDto;
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

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    @Operation(summary = "잔액 충전", description = "유저의 POINT를 충전합니다.")
    @PostMapping("/point/charge")
    public ResponseEntity<ChargeResponse> tempReservationSeat(@RequestBody ChargeRequest request){
        ChargeDto chargeDto = userCommandService.chargePoint(request.getUserId(),
                request.getAmount());
        return ResponseEntity.ok(ChargeResponse.of(chargeDto.userId(), chargeDto.Amount()));
    }

    @Operation(summary = "잔액 조회", description = "유저의 POINT를 조회합니다.")
    @GetMapping("/point/{userId}")
    public ResponseEntity<PointResponse> tempReservationSeat(
            @PathVariable Long userId
    ){
        ChargeDto chargeDto = userQueryService.getPoint(userId);
        return ResponseEntity.ok(PointResponse.of(chargeDto.userId(), chargeDto.Amount()));
    }

}
