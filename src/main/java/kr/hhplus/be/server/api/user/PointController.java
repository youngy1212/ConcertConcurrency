package kr.hhplus.be.server.api.user;


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

    @PostMapping("/point/charge")
    public ResponseEntity<ChargeResponse> tempReservationSeat(@RequestBody ChargeRequest request){
        return ResponseEntity.ok(ChargeResponse.of("uuid",1L));
    }

    @PostMapping("/point")
    public ResponseEntity<PointResponse> tempReservationSeat(@RequestBody PointRequest request){
        return ResponseEntity.ok(PointResponse.of(1L));
    }

}
