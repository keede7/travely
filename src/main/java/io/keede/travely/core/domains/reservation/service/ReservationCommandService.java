package io.keede.travely.core.domains.reservation.service;

import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import io.keede.travely.core.domains.reservation.dto.ReservationDto;
import io.keede.travely.core.domains.reservation.entity.Reservation;
import io.keede.travely.core.domains.reservation.entity.ReservationRepository;
import io.keede.travely.core.domains.user.entity.User;
import io.keede.travely.core.domains.user.entity.UserRepository;
import io.keede.travely.core.exception.service.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author keede
* Created on 2023/11/25
*/
@Service
public class ReservationCommandService {

    private final ReservationRepository reservationRepository;
    private final LodgingRepository lodgingRepository;
    private final UserRepository userRepository;

    public ReservationCommandService(
            final ReservationRepository reservationRepository,
            final LodgingRepository lodgingRepository,
            final UserRepository userRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.lodgingRepository = lodgingRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void create(final ReservationDto.Create create) {
        Lodging lodging = this.lodgingRepository.findById(create.lodgingId())
                .orElseThrow(BusinessException::new);

        lodging.checkToAllowReservation();

        User user = this.userRepository.findById(create.userId())
                .orElseThrow(BusinessException::new);

        Reservation reservation = new Reservation(lodging, user);

        reservationRepository.save(reservation);
    }
}
