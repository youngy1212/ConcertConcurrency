package kr.hhplus.be.server.domain.reservation;

public interface ReservationStore {

    TemporaryReservation temporaryReservationSave(TemporaryReservation tempReservation);

    Reservation reservationSave(Reservation reservation);
}
