package kr.hhplus.be.server.domain.reservation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.common.entity.BaseEntity;
import kr.hhplus.be.server.domain.concert.model.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.model.Seat;
import kr.hhplus.be.server.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemporaryReservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temp_reservation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_schedule_id")
    private ConcertSchedule concertSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private LocalDateTime expiresAt;

    @Enumerated(EnumType.STRING)
    private TemporaryReservationStatus status;

    private String queueTokenId;

    @Builder
    public TemporaryReservation(ConcertSchedule concertSchedule, User user, Seat seat,
                                LocalDateTime expiresAt,
                                TemporaryReservationStatus status, String queueTokenId) {
        this.concertSchedule = concertSchedule;
        this.user = user;
        this.seat = seat;
        this.expiresAt = expiresAt;
        this.status = status;
        this.queueTokenId = queueTokenId;
    }


    public static TemporaryReservation create(ConcertSchedule concertSchedule, User user, Seat seat,LocalDateTime expiresAt, String queueTokenId) {
        return TemporaryReservation.builder()
                .concertSchedule(concertSchedule)
                .user(user)
                .seat(seat)
                .expiresAt(expiresAt)
                .status(TemporaryReservationStatus.RESERVED)
                .queueTokenId(queueTokenId)
                .build();
    }

    //예약 만료 확인
    public boolean isExpired(LocalDateTime time){
        return expiresAt != null && expiresAt.isBefore(time);
    }

}
