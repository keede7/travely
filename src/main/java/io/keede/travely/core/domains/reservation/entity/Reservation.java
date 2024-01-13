package io.keede.travely.core.domains.reservation.entity;

import io.keede.travely.core.config.entity.BaseEntity;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.payment.entity.Payment;
import io.keede.travely.core.domains.user.entity.User;
import io.keede.travely.core.external.payment.publish.RefundPayment;
import io.keede.travely.core.external.payment.publish.SettlePayment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

/**
* @author keede
* Created on 2023/11/25
*/

@Entity
@Table(name = "reservation_t")
@Where(clause = "is_delete = 'N'")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "reservation_id"))
public class Reservation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "lodging_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Lodging lodging;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "payment_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Payment payment;

    public Reservation(
            final Lodging lodging,
            final User user,
            final Payment payment
    ) {
        this.lodging = lodging;
        this.user = user;
        this.payment = payment;
    }

    public void cancel() {
        this.remove();
    }

    public RefundPayment toRefundPayment() {
        return new RefundPayment(
                this.payment
        );
    }

    public SettlePayment toSettlePayment(
           final Long lodgingId,
           final Long userId,
           final Payment payment
    ) {
        return new SettlePayment(
                lodgingId,
                userId,
                payment
        );
    }
}
