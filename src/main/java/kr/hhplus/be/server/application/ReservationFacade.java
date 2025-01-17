package kr.hhplus.be.server.application;


import kr.hhplus.be.server.application.dto.TempReservationDto;
import kr.hhplus.be.server.domain.concert.model.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.model.Seat;
import kr.hhplus.be.server.domain.concert.service.ConcertCommandService;
import kr.hhplus.be.server.domain.concert.service.ConcertQueryService;
import kr.hhplus.be.server.domain.reservation.model.TemporaryReservation;
import kr.hhplus.be.server.domain.reservation.service.ReservationCommandService;
import kr.hhplus.be.server.domain.user.model.User;
import kr.hhplus.be.server.domain.user.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationFacade {

    //좌석 예약
    private final ReservationCommandService reservationCommandService;
    private final ConcertQueryService concertQueryService;
    private final ConcertCommandService concertCommandService;
    private final UserQueryService userQueryService;

    @Transactional
    public TempReservationDto reserveTempSeat(Long userId, Long seat_id , Long ConcertScheduleId, String tokenId) {

        User user = userQueryService.getUserById(userId);
        ConcertSchedule concertSchedule = concertQueryService.getConcertSchedule(ConcertScheduleId);
        Seat seat = concertCommandService.findByIdLock(seat_id);
        seat.reserve();
        TemporaryReservation temporaryReservation = reservationCommandService.createTemporaryReservation(concertSchedule, user,
                seat, tokenId);
        return new TempReservationDto(temporaryReservation.getId(),temporaryReservation.getExpiresAt());
    }



}
