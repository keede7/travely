package io.keede.travely.core.domains.reservation.dto;

/**
 * @author kyh
 * Created on 2023/11/25
 */
public class ReservationDto {
    public record Create(
            Long lodgingId,
            Long userId
    ) {

    }

    public record MyReservation(
            Long reservationId
    ) {

    }

    public record MyReservations(
            Long userId
    ) {

    }
}