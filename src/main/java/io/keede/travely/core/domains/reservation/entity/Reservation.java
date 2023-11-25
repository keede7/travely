package io.keede.travely.core.domains.reservation.entity;

import io.keede.travely.core.config.entity.BaseEntity;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.user.entity.User;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
* @author keede
* Created on 2023/11/25
*/

@Entity
@Table(name = "reservation_t")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "reservation_id"))
public class Reservation extends BaseEntity {

    // 예약하기
    public void reserve(Lodging lodging, User user) {

    }

    // 예약 취소하기
    public void cancel() {

    }

    // 예약 변경하기
    public void change(Lodging lodging) {

    }

}
