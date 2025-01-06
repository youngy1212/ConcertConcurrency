package kr.hhplus.be.server.infrastructure.concert;

import java.util.Optional;
import kr.hhplus.be.server.domain.concert.Concert;
import kr.hhplus.be.server.domain.concert.ConcertReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ConcertReadImpl implements ConcertReader {

    private final ConcertJpaRepository concertJpaRepository;

    @Override
    public Optional<Concert> findById(final long id) {
        return concertJpaRepository.findById(id);
    }
}
