package io.keede.travely.core.domains.payment.entity;


import io.keede.travely.core.config.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

/**
 * @author kyh
 * Created on 2024/01/11
 */
@Entity
@Table(name = "payment_t")
@Where(clause = "is_delete = 'N'")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "payment_id"))
public class Payment extends BaseEntity {

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    private Payment(
            final BigDecimal price,
            final PaymentType paymentType,
            final PaymentStatus paymentStatus
    ) {
        this.price = price;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
    }

    public static Payment paid(
            final BigDecimal price
    ) {
        return new Payment(
                price,
                PaymentType.CARD,
                PaymentStatus.COMPLETE
        );
    }

}
