package kr.hhplus.be.server.infrastructure.user;

import java.util.Optional;
import kr.hhplus.be.server.domain.user.model.Point;
import kr.hhplus.be.server.domain.user.repository.UserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCommandImpl implements UserCommand {

    private final PointJpaRepository pointJpaRepository;

    @Override
    public Optional<Point> charge(Long userId) {
        return pointJpaRepository.findByUserId(userId);
    }
}
