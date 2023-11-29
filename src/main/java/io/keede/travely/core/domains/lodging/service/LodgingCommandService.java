package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.lodging.dto.LodgingDto;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import io.keede.travely.core.exception.ErrorResponse;
import io.keede.travely.core.exception.service.BusinessException;
import jakarta.annotation.PostConstruct;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


/**
* @author keede
* Created on 2023/10/21
*/
@Service
public class LodgingCommandService {

    private final LodgingRepository lodgingRepository;

    public LodgingCommandService(final LodgingRepository lodgingRepository) {
        this.lodgingRepository = lodgingRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {

        Lodging lodging = new Lodging(
                "가평펜션",
                10,
                "경기도 가평",
                LocalDateTime.MIN,
                LocalDateTime.MAX
        );

        lodgingRepository.save(lodging);
    }

    @Transactional
    public LodgingDto.Information create(LodgingDto.Create create) {

        Lodging entity = create.toEntity();

        lodgingRepository.save(entity);

        return entity.toInformation();
    }

    @Transactional
    public void remove(LodgingDto.Remove remove) {

        final Long removeId = remove.id();

        Lodging entity = lodgingRepository.findById(removeId)
                .orElseThrow(
                        () -> new BusinessException(
                                ErrorResponse.COMMON,
                                String.format("to Remove Id : %d", removeId)
                        )
                );

        entity.remove();

    }
}
