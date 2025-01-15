package kr.hhplus.be.server.domain.reservation.service;

import java.util.NoSuchElementException;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;
import kr.hhplus.be.server.domain.reservation.repository.ReservationQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationQueryService {

    private final ReservationQuery reservationQuery;

    public TemporaryReservation getTemporaryReservation(Long reservationId) {
        return reservationQuery.getTemporaryReservation(reservationId).orElseThrow(() -> new NoSuchElementException(
                "잘못된 결제 요청입니다."));

    }

}
