package kr.hhplus.be.server.infrastructure.reservation;

import java.util.Optional;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;
import kr.hhplus.be.server.domain.reservation.repository.ReservationReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservationReaderImpl implements ReservationReader {
    private final TemporaryReservationJpaRepository temporaryReservationJpaRepository;

    @Override
    public Optional<TemporaryReservation> getTemporaryReservation(Long reservationId) {
        return temporaryReservationJpaRepository.findById(reservationId);
    }
}
