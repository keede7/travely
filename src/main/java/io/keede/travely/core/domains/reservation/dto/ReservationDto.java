package io.keede.travely.core.domains.reservation.dto;

public class ReservationDto {
    public record Create(
            Long lodgingId,
            Long userId
    ) {

    }
}