package io.keede.travely.core.domains.lodging.entity;

import io.keede.travely.core.config.entity.BaseEntity;
import io.keede.travely.core.domains.lodging.dto.LodgingDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.exception.ErrorResponse;
import io.keede.travely.core.exception.service.BusinessException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


/**
* @author keede
* Created on 2023/10/21
*/
@Entity
@Getter
@Table(name = "lodging_t")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "lodging_id"))
public class Lodging extends BaseEntity {

    private String lodgingName;
    private Integer maxUserCount;
    private String address;
    @Column(name = "from_at")
    private LocalDateTime from;
    @Column(name = "to_at")
    private LocalDateTime to;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lodging")
    private Set<Reservation> reservations = new HashSet<>();

    public Lodging(
            final String lodgingName,
            final Integer maxUserCount,
            final String address,
            final LocalDateTime from,
            final LocalDateTime to) {
        this.lodgingName = lodgingName;
        this.maxUserCount = maxUserCount;
        this.address = address;
        this.from = from;
        this.to = to;
    }

    public static Lodging of(
            final String lodgingName,
            final Integer maxUserCount,
            final String address,
            final LocalDateTime from,
            final LocalDateTime to) {
        return new Lodging(
                lodgingName,
                maxUserCount,
                address,
                from,
                to
        );
    }

    public void 숙소정보_변경() {

    }

    public void 숙소_삭제() {

    }

    public LodgingDto.Information toInformation() {
        return new LodgingDto.Information(
                this.getId(),
                this.lodgingName,
                this.maxUserCount,
                this.address,
                bindToTimes(this.from),
                bindToTimes(this.to)
        );
    }

    private String bindToTimes(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void checkToAllowReservation() {
        if(this.isExceedMaximumUserCount()) {
            throw new BusinessException(ErrorResponse.EXCEED_RESERVATION_USER_COUNT);
        }
    }

    private boolean isExceedMaximumUserCount() {
        return this.reservations.size() >= this.maxUserCount;
    }
}
