package kr.hhplus.be.server.domain.reservation.repository;

import java.util.Optional;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;

public interface ReservationReader {

    Optional<TemporaryReservation> getTemporaryReservation(Long reservationId);
}
