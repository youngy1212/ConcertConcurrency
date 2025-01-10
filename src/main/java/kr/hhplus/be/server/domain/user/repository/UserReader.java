package kr.hhplus.be.server.domain.user.repository;

import java.util.Optional;
import kr.hhplus.be.server.domain.user.model.Point;
import kr.hhplus.be.server.domain.user.model.User;

public interface UserReader {
    Optional<User> findById(final Long findById);

    Optional<Point> findByPoint(final Long userId);
}
