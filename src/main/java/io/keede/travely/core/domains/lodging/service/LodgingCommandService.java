package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.lodging.dto.LodgingDto;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import org.springframework.stereotype.Service;


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

    public LodgingDto.Information create(LodgingDto.Create create) {

        Lodging entity = create.toEntity();

        lodgingRepository.save(entity);

        return entity.toInformation();
    }
}
