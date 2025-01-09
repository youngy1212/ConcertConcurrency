package kr.hhplus.be.server.domain.payment;

import kr.hhplus.be.server.domain.reservation.Reservation;
import kr.hhplus.be.server.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class PaymentService {

    private final PaymentStore paymentStore;

    public Payment savePayment( User user, Reservation reservation,Long amount, PaymentStatus paymentStatus){

        Payment payment = Payment.create(user, reservation, amount, paymentStatus);

        return paymentStore.savePayment(payment);
    }


}
