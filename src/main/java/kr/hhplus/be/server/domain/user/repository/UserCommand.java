package kr.hhplus.be.server.domain.user.repository;

import java.util.Optional;
import kr.hhplus.be.server.domain.user.model.Point;

public interface UserCommand {

    Optional<Point> charge(final Long userId);
}
