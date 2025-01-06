package kr.hhplus.be.server.infrastructure.user;

import java.util.Optional;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findById(Long userId) {
        return userJpaRepository.findById(userId);
    }
}