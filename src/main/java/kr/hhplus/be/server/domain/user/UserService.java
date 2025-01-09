package kr.hhplus.be.server.domain.user;

import kr.hhplus.be.server.domain.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReader userReader;

    public User getUserById(Long userId) {
        return userReader.findById(userId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));
    }

    public Point chargePoint(Long userId, Long amount) {

        if (amount == null || amount <= 0) {
            throw new CustomException("잘못 청구되었습니다.");
        }

        Point point = userReader.findByPoint(userId).orElseThrow(() -> new CustomException("포인트를 찾을 수 없습니다."));;
        point.charge(amount);

        return point;

    }

    public Point getPoint(Long userId) {
        return userReader.findByPoint(userId).orElseThrow(() -> new CustomException("포인트를 찾을 수 없습니다."));
    }

}
