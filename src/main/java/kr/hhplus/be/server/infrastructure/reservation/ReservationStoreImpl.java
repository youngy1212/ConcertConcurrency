package kr.hhplus.be.server.infrastructure.reservation;

import kr.hhplus.be.server.domain.reservation.ReservationStore;
import kr.hhplus.be.server.domain.reservation.TemporaryReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationStoreImpl implements ReservationStore {

    private final TemporaryReservationJpaRepository temporaryReservationJpaRepository;

    @Override
    public TemporaryReservation save(TemporaryReservation tempReservation) {
        return temporaryReservationJpaRepository.save(tempReservation);
    }
}
