package kr.hhplus.be.server.infrastructure.user;

import java.util.Optional;
import kr.hhplus.be.server.domain.user.model.Point;
import kr.hhplus.be.server.domain.user.model.User;
import kr.hhplus.be.server.domain.user.repository.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQueryImpl implements UserQuery {

    private final UserJpaRepository userJpaRepository;
    private final PointJpaRepository pointJpaRepository;

    @Override
    public Optional<User> findById(Long userId) {
        return userJpaRepository.findById(userId);
    }

    @Override
    public Optional<Point> findByPoint(Long userId) {
        return pointJpaRepository.findByUserId(userId);
    }
}