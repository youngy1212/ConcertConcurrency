package kr.hhplus.be.server.domain.reservation;

import java.util.Optional;

public interface ReservationReader {

    Optional<TemporaryReservation> getTemporaryReservation(Long reservationId);
}
