package kr.hhplus.be.server.domain.user.service;

import java.util.NoSuchElementException;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.user.model.Point;
import kr.hhplus.be.server.domain.user.repository.UserCommand;
import kr.hhplus.be.server.domain.user.service.dto.ChargeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private UserCommand userCommand;

    public ChargeDto chargePoint(Long userId, Long amount) {

        if (amount == null || amount <= 0) {
            throw new CustomException("잘못 청구되었습니다.");
        }

        Point point = userCommand.charge(userId).orElseThrow(() -> new NoSuchElementException("포인트를 찾을 수 없습니다."));
        point.charge(amount);

        return new ChargeDto(point.getUser().getId(), point.getAmount());

    }
}
