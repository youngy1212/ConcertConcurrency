package kr.hhplus.be.server.domain.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import kr.hhplus.be.server.domain.common.entity.BaseEntity;
import kr.hhplus.be.server.domain.reservation.Reservation;
import kr.hhplus.be.server.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    private Long amount;

    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Builder
    private Payment(User user, Reservation reservation, Long amount, PaymentStatus status) {
        this.user = user;
        this.reservation = reservation;
        this.amount = amount;
        this.status = status;
    }

    public static Payment create(User user, Reservation reservation, Long amount, PaymentStatus status) {
        return Payment.builder()
                .user(user)
                .reservation(reservation)
                .amount(amount)
                .status(status)
                .build();
    }



}
