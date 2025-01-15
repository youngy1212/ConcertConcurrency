package kr.hhplus.be.server.infrastructure.reservation;

import kr.hhplus.be.server.domain.reservation.model.Reservation;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;
import kr.hhplus.be.server.domain.reservation.repository.ReservationCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationCommandImpl implements ReservationCommand {

    private final TemporaryReservationJpaRepository temporaryReservationJpaRepository;
    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public TemporaryReservation temporaryReservationSave(TemporaryReservation tempReservation) {
        return temporaryReservationJpaRepository.save(tempReservation);
    }

    @Override
    public Reservation reservationSave(Reservation reservation) {
        return reservationJpaRepository.save(reservation);
    }
}
