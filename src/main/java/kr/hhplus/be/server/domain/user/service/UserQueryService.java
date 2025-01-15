package kr.hhplus.be.server.domain.user.service;

import java.util.NoSuchElementException;
import kr.hhplus.be.server.domain.user.model.Point;
import kr.hhplus.be.server.domain.user.model.User;
import kr.hhplus.be.server.domain.user.repository.UserQuery;
import kr.hhplus.be.server.domain.user.service.dto.ChargeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserQuery userQuery;

    public User getUserById(Long userId) {
        return userQuery.findById(userId)
                .orElseThrow(() -> new NoSuchElementException( "유저를 찾을 수 없습니다."));
    }

    public ChargeDto getPoint(Long userId) {
        Point point = userQuery.findByPoint(userId).orElseThrow(() -> new NoSuchElementException("포인트를 찾을 수 없습니다."));
        return new ChargeDto(point.getUser().getId(), point.getAmount());
    }

}
