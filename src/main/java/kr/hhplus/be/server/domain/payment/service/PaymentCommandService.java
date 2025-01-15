package kr.hhplus.be.server.domain.payment.service;

import kr.hhplus.be.server.domain.payment.model.Payment;
import kr.hhplus.be.server.domain.payment.model.PaymentStatus;
import kr.hhplus.be.server.domain.payment.repository.PaymentCommand;
import kr.hhplus.be.server.domain.reservation.model.Reservation;
import kr.hhplus.be.server.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class PaymentCommandService {

    private final PaymentCommand paymentCommand;

    public Payment savePayment(User user, Reservation reservation, Long amount, PaymentStatus paymentStatus){

        Payment payment = Payment.create(user, reservation, amount, paymentStatus);

        return paymentCommand.save(payment);
    }


}
