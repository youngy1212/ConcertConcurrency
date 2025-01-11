package kr.hhplus.be.server.domain.reservation.repository;

import kr.hhplus.be.server.domain.reservation.model.Reservation;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;

public interface ReservationStore {

    TemporaryReservation temporaryReservationSave(TemporaryReservation tempReservation);

    Reservation reservationSave(Reservation reservation);
}
