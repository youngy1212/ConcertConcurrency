package kr.hhplus.be.server.domain.user;

import java.util.Optional;

public interface UserReader {
    Optional<User> findById(final Long findById);
}
