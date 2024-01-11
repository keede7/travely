package io.keede.travely.core.domains.payment.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kyh
 * Created on 2024/01/11
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
