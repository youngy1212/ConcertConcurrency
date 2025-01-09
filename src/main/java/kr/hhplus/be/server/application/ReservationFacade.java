package kr.hhplus.be.server.application;

import kr.hhplus.be.server.api.reservation.dto.TempReservationResponse;
import kr.hhplus.be.server.domain.concert.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.ConcertService;
import kr.hhplus.be.server.domain.concert.Seat;
import kr.hhplus.be.server.domain.reservation.ReservationService;
import kr.hhplus.be.server.domain.reservation.TemporaryReservation;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationFacade {

    //좌석 예약
    private final ReservationService reservationService;
    private final ConcertService concertService;
    private final UserService userService;

    @Transactional
    public TempReservationResponse tempReserveSeat(Long userId, Long seat_id , Long ConcertScheduleId, String tokenId) {

        User user = userService.getUserById(userId);
        ConcertSchedule concertSchedule = concertService.getConcertSchedule(ConcertScheduleId);
        Seat seat = concertService.findByIdLock(seat_id);
        seat.reserve();
        TemporaryReservation temporaryReservation = reservationService.createTemporaryReservation(concertSchedule, user,
                seat, tokenId);
        return TempReservationResponse.of(temporaryReservation.getId(),temporaryReservation.getExpiresAt());
    }



}
