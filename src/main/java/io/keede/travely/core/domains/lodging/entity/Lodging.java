package io.keede.travely.core.domains.lodging.entity;

import io.keede.travely.core.config.entity.BaseEntity;
import io.keede.travely.core.domains.lodging.dto.LodgingDto;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
* @author keede
* Created on 2023/10/21
*/
@Entity
@Table(name = "lodging_t")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "lodging_id"))
public class Lodging extends BaseEntity {

    @Column(name = "lodging_name", nullable = false)
    private String lodgingName;
    private Integer maxUserCount;
    private String address;
    private LocalDateTime from;
    private LocalDateTime to;

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

    public String getLodgingName() {
        return this.lodgingName;
    }

    public Integer getMaxUserCount() {
        return this.maxUserCount;
    }

    public String getAddress() {
        return this.address;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
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
}
