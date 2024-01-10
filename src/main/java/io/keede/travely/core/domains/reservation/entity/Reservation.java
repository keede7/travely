package io.keede.travely.core.domains.reservation.entity;

import io.keede.travely.core.config.entity.BaseEntity;
import io.keede.travely.core.domains.lodging.entity.Lodging;
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

    // 예약 상태 관련 도메인
    public Reservation(
            final Lodging lodging,
            final User user
    ) {
        this.lodging = lodging;
        this.user = user;
    }

    // 예약 취소하기
    public void cancel() {
        this.remove();
    }

    // 예약 변경하기
    public void change(final Lodging lodging) {

    }

    public RefundPayment toRefundPayment() {
        return new RefundPayment(
                this.getId()
        );
    }

    public SettlePayment toSettlePayment(
           final Long lodgingId,
           final Long userId,
           final Reservation reservation
    ) {
        return new SettlePayment(
                lodgingId,
                userId,
                reservation
        );
    }
}
