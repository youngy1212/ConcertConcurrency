package kr.hhplus.be.server.domain.payment.repository;

import kr.hhplus.be.server.domain.payment.model.Payment;

public interface PaymentCommand {
    Payment save(Payment payment);
}
